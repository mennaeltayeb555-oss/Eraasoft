package org.example.task9.task2.dto.employeedto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.example.task9.task2.dto.emaildto.EmailRequestDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeWithEmailsRequestDTO {

    @Valid
    private EmployeeRequestDTO employee;

    @NotEmpty(message = "Email list must not be empty")
    @Valid
    private List<EmailRequestDTO> emails;
}
