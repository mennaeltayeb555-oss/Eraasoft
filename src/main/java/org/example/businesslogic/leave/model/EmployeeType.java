package org.example.businesslogic.leave.model;

public enum EmployeeType {

    FULL_TIME(21),
    PART_TIME(10),
    MANAGER(30);

    private final int yearlyLeaveDays;

    EmployeeType(int yearlyLeaveDays) {
        this.yearlyLeaveDays = yearlyLeaveDays;
    }

    public int getYearlyLeaveDays() {
        return yearlyLeaveDays;
    }
}
