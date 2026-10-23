package org.example.businesslogic.discount.dto;

import java.util.List;

public record PriceResponse(
        List<ItemPriceResponse> items,
        double subtotal,
        double totalDiscount,
        double shippingFee,
        boolean freeShipping,
        double finalPrice
) {
}
