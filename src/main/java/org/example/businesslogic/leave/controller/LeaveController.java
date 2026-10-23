package org.example.businesslogic.leave.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.leave.dto.SubmitLeaveRequest;
import org.example.businesslogic.leave.model.Employee;
import org.example.businesslogic.leave.model.LeaveRequest;
import org.example.businesslogic.leave.service.LeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @GetMapping
    public List<LeaveRequest> getAll() {
        return leaveService.getRequests();
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return leaveService.getEmployees();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveRequest submit(@Valid @RequestBody SubmitLeaveRequest request) {
        return leaveService.submit(request);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approve(@PathVariable Long id) {
        return leaveService.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest reject(@PathVariable Long id) {
        return leaveService.reject(id);
    }

    @PutMapping("/{id}/cancel")
    public LeaveRequest cancel(@PathVariable Long id) {
        return leaveService.cancel(id);
    }
}
