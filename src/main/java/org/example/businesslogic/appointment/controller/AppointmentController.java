package org.example.businesslogic.appointment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.appointment.dto.BookAppointmentRequest;
import org.example.businesslogic.appointment.model.Appointment;
import org.example.businesslogic.appointment.model.Doctor;
import org.example.businesslogic.appointment.service.AppointmentService;
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
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAll() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return appointmentService.getAllDoctors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment book(@Valid @RequestBody BookAppointmentRequest request) {
        return appointmentService.book(request);
    }

    @PutMapping("/{id}/cancel")
    public Appointment cancel(@PathVariable Long id) {
        return appointmentService.cancel(id);
    }
}
