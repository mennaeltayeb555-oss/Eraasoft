package org.example.businesslogic.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.delivery.dto.CreateDeliveryRequest;
import org.example.businesslogic.delivery.dto.UpdateStatusRequest;
import org.example.businesslogic.delivery.model.DeliveryOrder;
import org.example.businesslogic.delivery.model.Driver;
import org.example.businesslogic.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryOrder> getAll() {
        return deliveryService.getOrders();
    }

    @GetMapping("/drivers")
    public List<Driver> getDrivers() {
        return deliveryService.getDrivers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryOrder create(@Valid @RequestBody CreateDeliveryRequest request) {
        return deliveryService.create(request);
    }

    @PutMapping("/{id}/assign/{driverId}")
    public DeliveryOrder assign(@PathVariable Long id, @PathVariable Long driverId) {
        return deliveryService.assignDriver(id, driverId);
    }

    @PutMapping("/{id}/status")
    public DeliveryOrder updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest request) {
        return deliveryService.updateStatus(id, request.status());
    }

    @PutMapping("/{id}/cancel")
    public DeliveryOrder cancel(@PathVariable Long id) {
        return deliveryService.cancel(id);
    }
}
