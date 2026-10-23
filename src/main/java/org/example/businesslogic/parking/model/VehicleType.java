package org.example.businesslogic.parking.model;

public enum VehicleType {

    MOTORCYCLE(10, 5),
    CAR(20, 10),
    TRUCK(50, 30);

    private final double firstHourPrice;
    private final double extraHourPrice;

    VehicleType(double firstHourPrice, double extraHourPrice) {
        this.firstHourPrice = firstHourPrice;
        this.extraHourPrice = extraHourPrice;
    }

    public double getFirstHourPrice() {
        return firstHourPrice;
    }

    public double getExtraHourPrice() {
        return extraHourPrice;
    }
}
