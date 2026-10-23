package org.example.businesslogic.restaurant.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ReservationRequest(
        @NotNull Long tableId,
        @NotNull Long customerId,
        @Positive int guests,
        @NotNull LocalDateTime startTime
) {
}
