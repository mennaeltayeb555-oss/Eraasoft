package org.example.businesslogic.parking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParkingTicket implements BaseEntity {

    private Long id;
    private String plateNumber;
    private VehicleType vehicleType;
    private boolean vip;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;
    private TicketStatus status;
}
