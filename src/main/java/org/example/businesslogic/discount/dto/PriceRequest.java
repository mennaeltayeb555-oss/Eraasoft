package org.example.businesslogic.discount.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PriceRequest(
        @NotNull Long customerId,
        @NotEmpty @Valid List<ItemRequest> items,
        List<String> discountCodes
) {
}
