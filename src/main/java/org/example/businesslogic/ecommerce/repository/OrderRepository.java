package org.example.businesslogic.ecommerce.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.ecommerce.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InMemoryRepository<Order> {

    public boolean existsUnpaidByCustomerId(Long customerId) {
        return findAll().stream()
                .anyMatch(order -> order.getCustomerId().equals(customerId) && !order.isPaid());
    }
}
