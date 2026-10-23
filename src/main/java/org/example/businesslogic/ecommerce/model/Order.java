package org.example.businesslogic.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order implements BaseEntity {

    private Long id;
    private Long customerId;
    private List<OrderItem> items = new ArrayList<>();
    private double subtotal;
    private double shippingFee;
    private double total;
    private OrderStatus status;
    private boolean paid;
    private LocalDateTime createdAt;
}
