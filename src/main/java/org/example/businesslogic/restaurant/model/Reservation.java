package org.example.businesslogic.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Reservation implements BaseEntity {

    private Long id;
    private Long tableId;
    private Long customerId;
    private int guests;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private ReservationStatus status;

    public boolean isActive() {
        return status == ReservationStatus.PENDING || status == ReservationStatus.CONFIRMED;
    }
}
