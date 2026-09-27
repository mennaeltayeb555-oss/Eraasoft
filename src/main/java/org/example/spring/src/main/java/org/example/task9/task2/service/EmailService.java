package org.example.task9.task2.service;

import org.example.task9.task2.dto.emaildto.EmailRequestDTO;
import org.example.task9.task2.dto.emaildto.EmailResponseDTO;

import java.util.List;

public interface EmailService {

    EmailResponseDTO createEmail(EmailRequestDTO dto);

    EmailResponseDTO updateEmail(Long id, EmailRequestDTO dto);

    void deleteEmail(Long id);

    List<EmailResponseDTO> getAllEmails();

    List<EmailResponseDTO> getEmailsByName(String name);

    List<EmailResponseDTO> getEmailsByNames(List<String> names);

    EmailResponseDTO getEmailByContent(String content);
}