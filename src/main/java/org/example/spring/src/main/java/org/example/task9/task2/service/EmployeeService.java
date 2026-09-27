package org.example.task9.task2.service;

import org.example.task9.task2.dto.employeedto.EmployeeRequestDTO;
import org.example.task9.task2.dto.employeedto.EmployeeResponseDTO;
import org.example.task9.task2.dto.employeedto.EmployeeWithEmailsRequestDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getEmployeesByIds(List<Long> ids);

    List<EmployeeResponseDTO> getEmployeesByNames(List<String> names);

    EmployeeResponseDTO createEmployeeWithEmails(EmployeeWithEmailsRequestDTO dto);
}