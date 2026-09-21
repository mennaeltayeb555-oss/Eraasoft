package org.example.task9.task2.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.task9.task2.employee.dto.EmployeeRequestDTO;
import org.example.task9.task2.employee.dto.EmployeeResponseDTO;
import org.example.task9.task2.employee.dto.EmployeeWithEmailsRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/byIds")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(employeeService.getEmployeesByIds(ids));
    }

    @GetMapping("/byNames")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(employeeService.getEmployeesByNames(names));
    }

    @PostMapping("/with-emails")
    public ResponseEntity<EmployeeResponseDTO> createEmployeeWithEmails(
            @Valid @RequestBody EmployeeWithEmailsRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployeeWithEmails(dto));
    }
}
