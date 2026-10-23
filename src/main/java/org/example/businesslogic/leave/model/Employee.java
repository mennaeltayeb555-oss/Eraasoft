package org.example.businesslogic.leave.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
public class Employee implements BaseEntity {

    private Long id;
    private String name;
    private EmployeeType type;
    private int remainingDays;

    public Employee(String name, EmployeeType type) {
        this.name = name;
        this.type = type;
        this.remainingDays = type.getYearlyLeaveDays();
    }
}
