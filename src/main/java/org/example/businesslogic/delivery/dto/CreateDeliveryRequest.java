package org.example.businesslogic.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDeliveryRequest(
        @NotNull Long customerId,
        @NotBlank String pickupAddress,
        @NotBlank String dropoffAddress,
        @Positive double distanceKm,
        @Positive double weightKg
) {
}
