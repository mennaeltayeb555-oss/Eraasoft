package org.example.businesslogic.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double lineTotal;
}
