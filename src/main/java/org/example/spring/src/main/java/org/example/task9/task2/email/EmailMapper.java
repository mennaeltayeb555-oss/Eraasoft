package org.example.task9.task2.email;

import org.example.task9.task2.email.Email;
import org.example.task9.task2.email.dto.EmailRequestDTO;
import org.example.task9.task2.email.dto.EmailResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class EmailMapper {

    public static Email toEntity(EmailRequestDTO dto) {
        return Email.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .build();
    }

    public static EmailResponseDTO toResponseDTO(Email email) {
        return EmailResponseDTO.builder()
                .id(email.getId())
                .name(email.getName())
                .content(email.getContent())
                .employeeId(email.getEmployee() != null ? email.getEmployee().getId() : null)
                .build();
    }

    public static List<EmailResponseDTO> toResponseDTOList(List<Email> emails) {
        return emails.stream()
                .map(EmailMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
