package org.example.businesslogic.restaurant.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.restaurant.model.Reservation;
import org.example.businesslogic.restaurant.model.ReservationStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepository extends InMemoryRepository<Reservation> {

    public List<Reservation> findActiveByTableId(Long tableId) {
        return findAll().stream()
                .filter(r -> r.getTableId().equals(tableId))
                .filter(Reservation::isActive)
                .toList();
    }

    public long countUpcomingActiveByCustomerId(Long customerId) {
        LocalDateTime now = LocalDateTime.now();
        return findAll().stream()
                .filter(r -> r.getCustomerId().equals(customerId))
                .filter(Reservation::isActive)
                .filter(r -> r.getEndTime().isAfter(now))
                .count();
    }

    public List<Reservation> findPending() {
        return findAll().stream()
                .filter(r -> r.getStatus() == ReservationStatus.PENDING)
                .toList();
    }
}
