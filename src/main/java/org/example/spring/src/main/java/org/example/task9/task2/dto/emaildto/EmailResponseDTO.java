package org.example.task9.task2.dto.emaildto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailResponseDTO {
    private Long id;
    private String name;
    private String content;
    private Long employeeId;
}
