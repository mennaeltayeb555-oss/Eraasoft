package org.example.businesslogic.parking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.businesslogic.parking.model.VehicleType;

public record EnterRequest(
        @NotBlank String plateNumber,
        @NotNull VehicleType vehicleType,
        boolean vip
) {
}
