package org.example.task9.task2.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task9.task2.dto.emaildto.EmailRequestDTO;
import org.example.task9.task2.dto.emaildto.EmailResponseDTO;
import org.example.task9.task2.exception.ResourceNotFoundException;
import org.example.task9.task2.mapper.EmailMapper;
import org.example.task9.task2.model.Email;
import org.example.task9.task2.service.EmailService;
import org.example.task9.task2.model.EmailRepository;
import org.example.task9.task2.model.Employee;
import org.example.task9.task2.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmployeeRepository employeeRepository;

    @Override
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

    @Override
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

    @Override
    public void deleteEmail(Long id) {
        if (!emailRepository.existsById(id)) {
            throw new ResourceNotFoundException("Email not found with id: " + id);
        }
        emailRepository.deleteById(id);
    }

    @Override
    public List<EmailResponseDTO> getAllEmails() {
        return EmailMapper.toResponseDTOList(emailRepository.findAll());
    }

    @Override
    public List<EmailResponseDTO> getEmailsByName(String name) {
        return EmailMapper.toResponseDTOList(emailRepository.findByName(name));
    }

    @Override
    public List<EmailResponseDTO> getEmailsByNames(List<String> names) {
        return EmailMapper.toResponseDTOList(emailRepository.findByNameIn(names));
    }

    @Override
    public EmailResponseDTO getEmailByContent(String content) {
        Email email = emailRepository.findByContent(content)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found with content: " + content));
        return EmailMapper.toResponseDTO(email);
    }
}