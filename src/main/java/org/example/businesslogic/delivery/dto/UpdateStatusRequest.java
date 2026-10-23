package org.example.businesslogic.delivery.dto;

import jakarta.validation.constraints.NotNull;
import org.example.businesslogic.delivery.model.DeliveryStatus;

public record UpdateStatusRequest(@NotNull DeliveryStatus status) {
}
