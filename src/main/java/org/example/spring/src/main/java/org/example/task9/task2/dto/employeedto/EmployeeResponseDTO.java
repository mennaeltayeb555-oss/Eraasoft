package org.example.task9.task2.dto.employeedto;

import lombok.*;
import org.example.task9.task2.dto.emaildto.EmailResponseDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private List<EmailResponseDTO> emails;
}
