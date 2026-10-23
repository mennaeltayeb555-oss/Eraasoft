package org.example.businesslogic.discount.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemRequest(
        @NotNull Long productId,
        @Positive int quantity
) {
}
