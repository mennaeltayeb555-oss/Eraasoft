package org.example.businesslogic.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryOrder implements BaseEntity {

    private Long id;
    private Long customerId;
    private String pickupAddress;
    private String dropoffAddress;
    private double distanceKm;
    private double weightKg;
    private double fee;
    private Long driverId;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
}
