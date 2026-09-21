package org.example.task9.task2.email;

import lombok.RequiredArgsConstructor;
import org.example.task9.task2.email.dto.EmailRequestDTO;
import org.example.task9.task2.email.dto.EmailResponseDTO;
import org.example.task9.task2.email.EmailMapper;
import org.example.task9.task2.employee.Employee;
import org.example.task9.task2.employee.EmployeeRepository;
import org.example.task9.task2.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmployeeRepository employeeRepository;

    public EmailResponseDTO createEmail(EmailRequestDTO dto) {
        Email email = EmailMapper.toEntity(dto);

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Employee not found with id: " + dto.getEmployeeId()));
            email.setEmployee(employee);
        }

        return EmailMapper.toResponseDTO(emailRepository.save(email));
    }

    public EmailResponseDTO updateEmail(Long id, EmailRequestDTO dto) {
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found with id: " + id));

        email.setName(dto.getName());
        email.setContent(dto.getContent());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Employee not found with id: " + dto.getEmployeeId()));
            email.setEmployee(employee);
        }

        return EmailMapper.toResponseDTO(emailRepository.save(email));
    }

    public void deleteEmail(Long id) {
        if (!emailRepository.existsById(id)) {
            throw new ResourceNotFoundException("Email not found with id: " + id);
        }
        emailRepository.deleteById(id);
    }

    public List<EmailResponseDTO> getAllEmails() {
        return EmailMapper.toResponseDTOList(emailRepository.findAll());
    }

    public List<EmailResponseDTO> getEmailsByName(String name) {
        return EmailMapper.toResponseDTOList(emailRepository.findByName(name));
    }

    public List<EmailResponseDTO> getEmailsByNames(List<String> names) {
        return EmailMapper.toResponseDTOList(emailRepository.findByNameIn(names));
    }

    public EmailResponseDTO getEmailByContent(String content) {
        Email email = emailRepository.findByContent(content)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found with content: " + content));
        return EmailMapper.toResponseDTO(email);
    }
}