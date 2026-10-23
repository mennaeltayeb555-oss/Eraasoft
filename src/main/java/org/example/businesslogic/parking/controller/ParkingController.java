package org.example.businesslogic.parking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.parking.dto.EnterRequest;
import org.example.businesslogic.parking.dto.ParkingStatusResponse;
import org.example.businesslogic.parking.model.ParkingTicket;
import org.example.businesslogic.parking.service.ParkingService;
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
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/tickets")
    public List<ParkingTicket> getTickets() {
        return parkingService.getTickets();
    }

    @GetMapping("/status")
    public ParkingStatusResponse getStatus() {
        return parkingService.getStatus();
    }

    @PostMapping("/enter")
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingTicket enter(@Valid @RequestBody EnterRequest request) {
        return parkingService.enter(request);
    }

    @PutMapping("/tickets/{id}/exit")
    public ParkingTicket exit(@PathVariable Long id) {
        return parkingService.exit(id);
    }

    @PutMapping("/lost-ticket/{plateNumber}")
    public ParkingTicket lostTicket(@PathVariable String plateNumber) {
        return parkingService.lostTicket(plateNumber);
    }
}
