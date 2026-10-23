package org.example.businesslogic.appointment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.example.businesslogic.appointment.model.ServiceType;

import java.time.LocalDateTime;

public record BookAppointmentRequest(
        @NotNull Long doctorId,
        @NotNull Long patientId,
        @NotNull ServiceType serviceType,
        @NotNull @Future LocalDateTime startTime
) {
}
