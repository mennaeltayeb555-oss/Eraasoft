package org.example.task9.task2.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequestDTO {

    @NotBlank(message = "Name must not be null or empty")
    private String name;

    @NotBlank(message = "Content must not be null or empty")
    @Email(message = "Content must be a valid email address")
    private String content;

    private Long employeeId; // اختياري، لربط الايميل بموظف موجود already
}
