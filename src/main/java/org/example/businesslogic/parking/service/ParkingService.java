package org.example.businesslogic.parking.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.parking.dto.EnterRequest;
import org.example.businesslogic.parking.dto.ParkingStatusResponse;
import org.example.businesslogic.parking.model.ParkingTicket;
import org.example.businesslogic.parking.model.TicketStatus;
import org.example.businesslogic.parking.model.VehicleType;
import org.example.businesslogic.parking.repository.ParkingTicketRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.example.businesslogic.common.util.MoneyUtils.round;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private static final int CAPACITY = 20;
    private static final double VIP_DISCOUNT_PERCENT = 20;
    private static final double LOST_TICKET_PENALTY = 200;

    private final ParkingTicketRepository ticketRepository;

    public List<ParkingTicket> getTickets() {
        return ticketRepository.findAll();
    }

    public ParkingStatusResponse getStatus() {
        long occupied = ticketRepository.countActive();
        return new ParkingStatusResponse(CAPACITY, occupied, CAPACITY - occupied);
    }

    public synchronized ParkingTicket enter(EnterRequest request) {
        String plateNumber = normalizePlate(request.plateNumber());

        if (ticketRepository.findActiveByPlateNumber(plateNumber).isPresent()) {
            throw new BusinessException("Vehicle " + plateNumber + " is already inside the parking");
        }
        if (ticketRepository.countActive() >= CAPACITY) {
            throw new BusinessException("Parking is full");
        }

        ParkingTicket ticket = new ParkingTicket();
        ticket.setPlateNumber(plateNumber);
        ticket.setVehicleType(request.vehicleType());
        ticket.setVip(request.vip());
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ACTIVE);
        return ticketRepository.save(ticket);
    }

    public synchronized ParkingTicket exit(Long ticketId) {
        ParkingTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        if (ticket.getStatus() != TicketStatus.ACTIVE) {
            throw new BusinessException("Ticket is already closed");
        }

        LocalDateTime now = LocalDateTime.now();
        ticket.setExitTime(now);
        ticket.setFee(calculateFee(ticket, now));
        ticket.setStatus(TicketStatus.PAID);
        return ticketRepository.save(ticket);
    }

    // the driver lost the ticket, so we find the car by plate number and add the penalty
    public synchronized ParkingTicket lostTicket(String plateNumber) {
        ParkingTicket ticket = ticketRepository.findActiveByPlateNumber(normalizePlate(plateNumber))
                .orElseThrow(() -> new NotFoundException("No active ticket for vehicle " + plateNumber));

        LocalDateTime now = LocalDateTime.now();
        ticket.setExitTime(now);
        ticket.setFee(round(calculateFee(ticket, now) + LOST_TICKET_PENALTY));
        ticket.setStatus(TicketStatus.LOST);
        return ticketRepository.save(ticket);
    }

    private double calculateFee(ParkingTicket ticket, LocalDateTime exitTime) {
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();

        // any part of an hour counts as a full hour, minimum is 1 hour
        long hours = Math.max(1, (minutes + 59) / 60);

        VehicleType type = ticket.getVehicleType();
        double fee = type.getFirstHourPrice() + (hours - 1) * type.getExtraHourPrice();

        if (ticket.isVip()) {
            fee = fee - fee * VIP_DISCOUNT_PERCENT / 100;
        }
        return round(fee);
    }

    private String normalizePlate(String plateNumber) {
        return plateNumber.trim().toUpperCase();
    }
}
