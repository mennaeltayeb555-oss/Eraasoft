package org.example.task9.task2.employee.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {

    @NotBlank(message = "Name must not be null or empty")
    private String name;

    @DecimalMin(value = "15", inclusive = false, message = "Age must be greater than 15")
    @DecimalMax(value = "40", inclusive = false, message = "Age must be less than 40")
    private Integer age;

    @DecimalMin(value = "5000", inclusive = false, message = "Salary must be greater than 5000")
    @DecimalMax(value = "10000", inclusive = false, message = "Salary must be less than 10000")
    private Double salary;
}
