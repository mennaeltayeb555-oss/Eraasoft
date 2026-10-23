package org.example.businesslogic.delivery.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.delivery.dto.CreateDeliveryRequest;
import org.example.businesslogic.delivery.model.DeliveryOrder;
import org.example.businesslogic.delivery.model.DeliveryStatus;
import org.example.businesslogic.delivery.model.DeliveryVehicle;
import org.example.businesslogic.delivery.model.Driver;
import org.example.businesslogic.delivery.repository.DeliveryOrderRepository;
import org.example.businesslogic.delivery.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.example.businesslogic.common.util.MoneyUtils.round;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private static final double BASE_FEE = 20;
    private static final double PRICE_PER_KM = 3;
    private static final double PRICE_PER_KG = 1.5;

    // long trips must be done by a truck
    private static final double LONG_DISTANCE_KM = 50;
    private static final DeliveryVehicle LONG_DISTANCE_VEHICLE = DeliveryVehicle.TRUCK;

    // these statuses are changed by their own endpoints (assign and cancel)
    private static final Set<DeliveryStatus> DRIVER_STATUSES =
            Set.of(DeliveryStatus.PICKED_UP, DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED);

    private final DeliveryOrderRepository orderRepository;
    private final DriverRepository driverRepository;

    public List<DeliveryOrder> getOrders() {
        return orderRepository.findAll();
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public DeliveryOrder create(CreateDeliveryRequest request) {
        if (!DeliveryVehicle.TRUCK.canCarry(request.weightKg())) {
            throw new BusinessException("Package is too heavy, max weight is " + DeliveryVehicle.TRUCK.getMaxWeightKg() + " kg");
        }

        DeliveryOrder order = new DeliveryOrder();
        order.setCustomerId(request.customerId());
        order.setPickupAddress(request.pickupAddress());
        order.setDropoffAddress(request.dropoffAddress());
        order.setDistanceKm(request.distanceKm());
        order.setWeightKg(request.weightKg());
        order.setFee(calculateFee(request.distanceKm(), request.weightKg()));
        order.setStatus(DeliveryStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public synchronized DeliveryOrder assignDriver(Long orderId, Long driverId) {
        DeliveryOrder order = findOrder(orderId);
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException("Driver not found"));

        checkTransition(order, DeliveryStatus.ASSIGNED);

        if (orderRepository.driverHasActiveDelivery(driver.getId())) {
            throw new BusinessException("Driver " + driver.getName() + " already has an active delivery");
        }
        if (!driver.getVehicle().canCarry(order.getWeightKg())) {
            throw new BusinessException(driver.getVehicle() + " can carry only " + driver.getVehicle().getMaxWeightKg() + " kg");
        }
        if (order.getDistanceKm() > LONG_DISTANCE_KM && driver.getVehicle() != LONG_DISTANCE_VEHICLE) {
            throw new BusinessException("Orders above " + LONG_DISTANCE_KM + " km need a " + LONG_DISTANCE_VEHICLE);
        }

        order.setDriverId(driver.getId());
        order.setStatus(DeliveryStatus.ASSIGNED);
        return orderRepository.save(order);
    }

    public synchronized DeliveryOrder updateStatus(Long orderId, DeliveryStatus newStatus) {
        if (!DRIVER_STATUSES.contains(newStatus)) {
            throw new BusinessException("Use the assign or cancel endpoint to set status " + newStatus);
        }

        DeliveryOrder order = findOrder(orderId);
        checkTransition(order, newStatus);

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public synchronized DeliveryOrder cancel(Long orderId) {
        DeliveryOrder order = findOrder(orderId);

        if (!order.getStatus().canMoveTo(DeliveryStatus.CANCELED)) {
            throw new BusinessException("Order can only be canceled before the driver picks it up");
        }

        order.setStatus(DeliveryStatus.CANCELED);
        return orderRepository.save(order);
    }

    private void checkTransition(DeliveryOrder order, DeliveryStatus next) {
        if (!order.getStatus().canMoveTo(next)) {
            throw new BusinessException("Cannot change status from " + order.getStatus() + " to " + next);
        }
    }

    private double calculateFee(double distanceKm, double weightKg) {
        return round(BASE_FEE + distanceKm * PRICE_PER_KM + weightKg * PRICE_PER_KG);
    }

    private DeliveryOrder findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery order not found"));
    }
}
