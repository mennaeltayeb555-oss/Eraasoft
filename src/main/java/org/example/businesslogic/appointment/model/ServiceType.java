package org.example.businesslogic.appointment.model;

public enum ServiceType {

    CONSULTATION(30),
    CHECKUP(45),
    DENTAL_CLEANING(60),
    SURGERY(120);

    private final int durationMinutes;

    ServiceType(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
