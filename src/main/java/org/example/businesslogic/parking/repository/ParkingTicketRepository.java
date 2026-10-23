package org.example.businesslogic.parking.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.parking.model.ParkingTicket;
import org.example.businesslogic.parking.model.TicketStatus;
import org.example.businesslogic.parking.model.VehicleType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ParkingTicketRepository extends InMemoryRepository<ParkingTicket> {

    public ParkingTicketRepository() {
        // a car that entered 3 hours ago, useful to test the extra hours price
        ParkingTicket ticket = new ParkingTicket();
        ticket.setPlateNumber("ABC-123");
        ticket.setVehicleType(VehicleType.CAR);
        ticket.setVip(false);
        ticket.setEntryTime(LocalDateTime.now().minusHours(3).minusMinutes(10));
        ticket.setStatus(TicketStatus.ACTIVE);
        save(ticket);
    }

    public Optional<ParkingTicket> findActiveByPlateNumber(String plateNumber) {
        return findAll().stream()
                .filter(t -> t.getStatus() == TicketStatus.ACTIVE)
                .filter(t -> t.getPlateNumber().equals(plateNumber))
                .findFirst();
    }

    public long countActive() {
        return findAll().stream()
                .filter(t -> t.getStatus() == TicketStatus.ACTIVE)
                .count();
    }
}
