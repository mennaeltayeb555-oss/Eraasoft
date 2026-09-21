package org.example.task9.task2.employee;

import lombok.RequiredArgsConstructor;
import org.example.task9.task2.employee.dto.EmployeeRequestDTO;
import org.example.task9.task2.employee.dto.EmployeeResponseDTO;
import org.example.task9.task2.employee.dto.EmployeeWithEmailsRequestDTO;
import org.example.task9.task2.employee.EmployeeMapper;
import org.example.task9.task2.email.Email;
import org.example.task9.task2.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Employee employee = EmployeeMapper.toEntity(dto);
        Employee saved = employeeRepository.save(employee);
        return EmployeeMapper.toResponseDTO(saved);
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setSalary(dto.getSalary());

        return EmployeeMapper.toResponseDTO(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
    public List<EmployeeResponseDTO> getAllEmployees() {
        return EmployeeMapper.toResponseDTOList(employeeRepository.findAll());
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeMapper.toResponseDTO(employee);
    }

    public List<EmployeeResponseDTO> getEmployeesByIds(List<Long> ids) {
        return EmployeeMapper.toResponseDTOList(employeeRepository.findAllById(ids));
    }

    public List<EmployeeResponseDTO> getEmployeesByNames(List<String> names) {
        return EmployeeMapper.toResponseDTOList(employeeRepository.findByNameIn(names));
    }

    public EmployeeResponseDTO createEmployeeWithEmails(EmployeeWithEmailsRequestDTO dto) {
        Employee employee = EmployeeMapper.toEntity(dto.getEmployee());

        List<Email> emails = dto.getEmails().stream()
                .map(emailDto -> {
                    Email email = new Email();
                    email.setName(emailDto.getName());
                    email.setContent(emailDto.getContent());
                    email.setEmployee(employee);
                    return email;
                })
                .collect(Collectors.toList());

        employee.setEmails(emails);

        Employee saved = employeeRepository.save(employee); // cascade=ALL هتحفظ الإيميلات كمان تلقائيًا
        return EmployeeMapper.toResponseDTO(saved);
    }
}
