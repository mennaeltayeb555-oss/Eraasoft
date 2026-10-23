package org.example.businesslogic.discount.dto;

public record ItemPriceResponse(
        String productName,
        int quantity,
        double unitPrice,
        double discountPercent,
        double priceAfterDiscount
) {
}
