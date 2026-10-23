package org.example.businesslogic.restaurant.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.restaurant.dto.ReservationRequest;
import org.example.businesslogic.restaurant.model.Reservation;
import org.example.businesslogic.restaurant.model.RestaurantTable;
import org.example.businesslogic.restaurant.service.ReservationService;
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
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getReservations();
    }

    @GetMapping("/tables")
    public List<RestaurantTable> getTables() {
        return reservationService.getTables();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation reserve(@Valid @RequestBody ReservationRequest request) {
        return reservationService.reserve(request);
    }

    @PutMapping("/{id}/confirm")
    public Reservation confirm(@PathVariable Long id) {
        return reservationService.confirm(id);
    }

    @PutMapping("/{id}/cancel")
    public Reservation cancel(@PathVariable Long id) {
        return reservationService.cancel(id);
    }
}
