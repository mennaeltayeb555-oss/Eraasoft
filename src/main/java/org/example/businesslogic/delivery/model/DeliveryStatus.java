package org.example.businesslogic.delivery.model;

// CREATED -> ASSIGNED -> PICKED_UP -> IN_TRANSIT -> DELIVERED
// the order can be canceled only before pick up
public enum DeliveryStatus {

    CREATED,
    ASSIGNED,
    PICKED_UP,
    IN_TRANSIT,
    DELIVERED,
    CANCELED;

    public boolean canMoveTo(DeliveryStatus next) {
        return switch (this) {
            case CREATED -> next == ASSIGNED || next == CANCELED;
            case ASSIGNED -> next == PICKED_UP || next == CANCELED;
            case PICKED_UP -> next == IN_TRANSIT;
            case IN_TRANSIT -> next == DELIVERED;
            case DELIVERED, CANCELED -> false;
        };
    }

    public boolean isActive() {
        return this == ASSIGNED || this == PICKED_UP || this == IN_TRANSIT;
    }
}
