package org.example.businesslogic.library.model;

public enum MemberType {

    NORMAL(14),
    PREMIUM(30);

    private final int borrowDays;

    MemberType(int borrowDays) {
        this.borrowDays = borrowDays;
    }

    public int getBorrowDays() {
        return borrowDays;
    }
}
