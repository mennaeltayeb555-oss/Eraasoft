package org.example.task9.task2.employee;

import org.example.task9.task2.employee.Employee;
import org.example.task9.task2.employee.dto.EmployeeRequestDTO;
import org.example.task9.task2.employee.dto.EmployeeResponseDTO;
import org.example.task9.task2.email.EmailMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDTO dto) {
        return Employee.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .salary(dto.getSalary())
                .build();
    }

    public static EmployeeResponseDTO toResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .age(employee.getAge())
                .salary(employee.getSalary())
                .emails(employee.getEmails() == null ? Collections.emptyList() :
                        employee.getEmails().stream()
                                .map(EmailMapper::toResponseDTO)
                                .collect(Collectors.toList()))
                .build();
    }

    public static List<EmployeeResponseDTO> toResponseDTOList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
