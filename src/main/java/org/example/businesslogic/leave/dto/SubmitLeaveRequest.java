package org.example.businesslogic.leave.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SubmitLeaveRequest(
        @NotNull Long employeeId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        String reason
) {
}
