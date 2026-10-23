package org.example.businesslogic.delivery.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.delivery.model.DeliveryOrder;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryOrderRepository extends InMemoryRepository<DeliveryOrder> {

    public boolean driverHasActiveDelivery(Long driverId) {
        return findAll().stream()
                .anyMatch(order -> driverId.equals(order.getDriverId()) && order.getStatus().isActive());
    }
}
