package org.example.businesslogic.enrollment.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollRequest(
        @NotNull Long studentId,
        @NotNull Long courseId
) {
}
