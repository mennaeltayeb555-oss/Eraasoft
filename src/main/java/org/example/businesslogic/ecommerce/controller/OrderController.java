package org.example.businesslogic.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.ecommerce.dto.CreateOrderRequest;
import org.example.businesslogic.ecommerce.dto.UpdateOrderRequest;
import org.example.businesslogic.ecommerce.model.Order;
import org.example.businesslogic.ecommerce.model.StoreProduct;
import org.example.businesslogic.ecommerce.service.OrderService;
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
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getOrders();
    }

    @GetMapping("/products")
    public List<StoreProduct> getProducts() {
        return orderService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.create(request);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @Valid @RequestBody UpdateOrderRequest request) {
        return orderService.update(id, request);
    }

    @PutMapping("/{id}/confirm")
    public Order confirm(@PathVariable Long id) {
        return orderService.confirm(id);
    }

    @PutMapping("/{id}/pay")
    public Order pay(@PathVariable Long id) {
        return orderService.pay(id);
    }
}
