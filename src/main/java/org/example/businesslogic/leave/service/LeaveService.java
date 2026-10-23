package org.example.businesslogic.leave.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.leave.dto.SubmitLeaveRequest;
import org.example.businesslogic.leave.model.Employee;
import org.example.businesslogic.leave.model.LeaveRequest;
import org.example.businesslogic.leave.model.LeaveStatus;
import org.example.businesslogic.leave.repository.EmployeeRepository;
import org.example.businesslogic.leave.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private static final Set<DayOfWeek> WEEKEND = Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final EmployeeRepository employeeRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public List<LeaveRequest> getRequests() {
        return leaveRequestRepository.findAll();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public synchronized LeaveRequest submit(SubmitLeaveRequest request) {
        Employee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        LocalDate start = request.startDate();
        LocalDate end = request.endDate();

        if (start.isBefore(LocalDate.now())) {
            throw new BusinessException("Start date cannot be before today");
        }
        if (end.isBefore(start)) {
            throw new BusinessException("End date cannot be before start date");
        }

        checkNoOverlap(employee.getId(), start, end);

        int days = countWorkingDays(start, end);
        if (days == 0) {
            throw new BusinessException("The selected period has only weekend days");
        }
        if (days > employee.getRemainingDays()) {
            throw new BusinessException("Not enough leave balance, requested " + days
                    + " days but only " + employee.getRemainingDays() + " left");
        }

        // we take the days from the balance now so another request can't use them too
        employee.setRemainingDays(employee.getRemainingDays() - days);
        employeeRepository.save(employee);

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployeeId(employee.getId());
        leaveRequest.setStartDate(start);
        leaveRequest.setEndDate(end);
        leaveRequest.setDays(days);
        leaveRequest.setReason(request.reason());
        leaveRequest.setStatus(LeaveStatus.PENDING);
        return leaveRequestRepository.save(leaveRequest);
    }

    public synchronized LeaveRequest approve(Long id) {
        LeaveRequest leaveRequest = findRequest(id);
        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new BusinessException("Only pending requests can be approved");
        }
        leaveRequest.setStatus(LeaveStatus.APPROVED);
        return leaveRequestRepository.save(leaveRequest);
    }

    public synchronized LeaveRequest reject(Long id) {
        LeaveRequest leaveRequest = findRequest(id);
        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new BusinessException("Only pending requests can be rejected");
        }
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        restoreBalance(leaveRequest);
        return leaveRequestRepository.save(leaveRequest);
    }

    public synchronized LeaveRequest cancel(Long id) {
        LeaveRequest leaveRequest = findRequest(id);
        if (!leaveRequest.isActive()) {
            throw new BusinessException("Request is already " + leaveRequest.getStatus());
        }
        if (!leaveRequest.getStartDate().isAfter(LocalDate.now())) {
            throw new BusinessException("Leave already started, it cannot be canceled");
        }
        leaveRequest.setStatus(LeaveStatus.CANCELED);
        restoreBalance(leaveRequest);
        return leaveRequestRepository.save(leaveRequest);
    }

    private void restoreBalance(LeaveRequest leaveRequest) {
        Employee employee = employeeRepository.findById(leaveRequest.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found"));
        employee.setRemainingDays(employee.getRemainingDays() + leaveRequest.getDays());
        employeeRepository.save(employee);
    }

    private void checkNoOverlap(Long employeeId, LocalDate start, LocalDate end) {
        for (LeaveRequest other : leaveRequestRepository.findActiveByEmployeeId(employeeId)) {
            boolean overlaps = !start.isAfter(other.getEndDate()) && !end.isBefore(other.getStartDate());
            if (overlaps) {
                throw new BusinessException("You already have leave from " + other.getStartDate() + " to " + other.getEndDate());
            }
        }
    }

    private int countWorkingDays(LocalDate start, LocalDate end) {
        int days = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (!WEEKEND.contains(date.getDayOfWeek())) {
                days++;
            }
        }
        return days;
    }

    private LeaveRequest findRequest(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Leave request not found"));
    }
}
