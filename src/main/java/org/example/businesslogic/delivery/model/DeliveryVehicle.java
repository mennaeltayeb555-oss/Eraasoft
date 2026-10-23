package org.example.businesslogic.delivery.model;

public enum DeliveryVehicle {

    BIKE(10),
    CAR(100),
    VAN(500),
    TRUCK(2000);

    private final double maxWeightKg;

    DeliveryVehicle(double maxWeightKg) {
        this.maxWeightKg = maxWeightKg;
    }

    public double getMaxWeightKg() {
        return maxWeightKg;
    }

    public boolean canCarry(double weightKg) {
        return weightKg <= maxWeightKg;
    }
}
