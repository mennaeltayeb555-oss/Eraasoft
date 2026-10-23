package org.example.businesslogic.leave.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.leave.model.LeaveRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaveRequestRepository extends InMemoryRepository<LeaveRequest> {

    public List<LeaveRequest> findActiveByEmployeeId(Long employeeId) {
        return findAll().stream()
                .filter(r -> r.getEmployeeId().equals(employeeId))
                .filter(LeaveRequest::isActive)
                .toList();
    }
}
