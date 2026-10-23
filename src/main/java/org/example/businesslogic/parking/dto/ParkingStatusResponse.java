package org.example.businesslogic.parking.dto;

public record ParkingStatusResponse(int capacity, long occupied, long available) {
}
