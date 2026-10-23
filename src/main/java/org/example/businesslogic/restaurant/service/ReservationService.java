package org.example.businesslogic.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.restaurant.dto.ReservationRequest;
import org.example.businesslogic.restaurant.model.Reservation;
import org.example.businesslogic.restaurant.model.ReservationStatus;
import org.example.businesslogic.restaurant.model.RestaurantTable;
import org.example.businesslogic.restaurant.repository.ReservationRepository;
import org.example.businesslogic.restaurant.repository.TableRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private static final LocalTime OPENING_TIME = LocalTime.of(12, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(23, 0);
    private static final int RESERVATION_HOURS = 2;
    private static final int MIN_MINUTES_IN_ADVANCE = 30;
    private static final int CANCEL_BEFORE_HOURS = 1;
    private static final int CONFIRM_WITHIN_MINUTES = 15;
    private static final int MAX_ACTIVE_RESERVATIONS = 2;

    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<RestaurantTable> getTables() {
        return tableRepository.findAll();
    }

    public synchronized Reservation reserve(ReservationRequest request) {
        expireOldPendingReservations();

        RestaurantTable table = tableRepository.findById(request.tableId())
                .orElseThrow(() -> new NotFoundException("Table not found"));

        if (request.guests() > table.getCapacity()) {
            throw new BusinessException("Table " + table.getTableNumber() + " can only take " + table.getCapacity() + " guests");
        }

        LocalDateTime start = request.startTime();
        LocalDateTime end = start.plusHours(RESERVATION_HOURS);

        if (start.isBefore(LocalDateTime.now().plusMinutes(MIN_MINUTES_IN_ADVANCE))) {
            throw new BusinessException("Reservation must be made at least " + MIN_MINUTES_IN_ADVANCE + " minutes in advance");
        }

        checkWorkingHours(start, end);
        checkTableIsFree(table.getId(), start, end);

        long activeCount = reservationRepository.countUpcomingActiveByCustomerId(request.customerId());
        if (activeCount >= MAX_ACTIVE_RESERVATIONS) {
            throw new BusinessException("Customer cannot have more than " + MAX_ACTIVE_RESERVATIONS + " active reservations");
        }

        Reservation reservation = new Reservation();
        reservation.setTableId(table.getId());
        reservation.setCustomerId(request.customerId());
        reservation.setGuests(request.guests());
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setStatus(ReservationStatus.PENDING);
        return reservationRepository.save(reservation);
    }

    public synchronized Reservation confirm(Long id) {
        Reservation reservation = findReservation(id);

        if (reservation.getStatus() == ReservationStatus.PENDING && isConfirmTimeOver(reservation)) {
            reservation.setStatus(ReservationStatus.EXPIRED);
            reservationRepository.save(reservation);
        }

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new BusinessException("Only pending reservations can be confirmed, current status is " + reservation.getStatus());
        }

        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    public synchronized Reservation cancel(Long id) {
        Reservation reservation = findReservation(id);

        if (!reservation.isActive()) {
            throw new BusinessException("Reservation is already " + reservation.getStatus());
        }

        LocalDateTime lastCancelTime = reservation.getStartTime().minusHours(CANCEL_BEFORE_HOURS);
        if (LocalDateTime.now().isAfter(lastCancelTime)) {
            throw new BusinessException("You can only cancel at least " + CANCEL_BEFORE_HOURS + " hour before the reservation");
        }

        reservation.setStatus(ReservationStatus.CANCELED);
        return reservationRepository.save(reservation);
    }

    // runs every minute and expires reservations that were not confirmed in time
    @Scheduled(fixedRate = 60_000)
    public synchronized void expireOldPendingReservations() {
        for (Reservation reservation : reservationRepository.findPending()) {
            if (isConfirmTimeOver(reservation)) {
                reservation.setStatus(ReservationStatus.EXPIRED);
                reservationRepository.save(reservation);
            }
        }
    }

    private boolean isConfirmTimeOver(Reservation reservation) {
        return LocalDateTime.now().isAfter(reservation.getCreatedAt().plusMinutes(CONFIRM_WITHIN_MINUTES));
    }

    private void checkWorkingHours(LocalDateTime start, LocalDateTime end) {
        boolean sameDay = start.toLocalDate().equals(end.toLocalDate());
        boolean afterOpening = !start.toLocalTime().isBefore(OPENING_TIME);
        boolean beforeClosing = !end.toLocalTime().isAfter(CLOSING_TIME);

        if (!sameDay || !afterOpening || !beforeClosing) {
            throw new BusinessException("Restaurant works from " + OPENING_TIME + " to " + CLOSING_TIME
                    + " and a reservation takes " + RESERVATION_HOURS + " hours");
        }
    }

    private void checkTableIsFree(Long tableId, LocalDateTime start, LocalDateTime end) {
        for (Reservation other : reservationRepository.findActiveByTableId(tableId)) {
            boolean overlaps = start.isBefore(other.getEndTime()) && end.isAfter(other.getStartTime());
            if (overlaps) {
                throw new BusinessException("Table is already reserved from " + other.getStartTime() + " to " + other.getEndTime());
            }
        }
    }

    private Reservation findReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
    }
}
