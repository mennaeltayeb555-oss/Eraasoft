package org.example.businesslogic.leave.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LeaveRequest implements BaseEntity {

    private Long id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int days;
    private String reason;
    private LeaveStatus status;

    public boolean isActive() {
        return status == LeaveStatus.PENDING || status == LeaveStatus.APPROVED;
    }
}
