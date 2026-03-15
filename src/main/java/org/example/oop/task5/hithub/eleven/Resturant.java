package org.example.oop.task5.hithub.eleven;

public abstract class Resturant {
    protected String orderName;

    public Resturant(String orderName) {
        this.orderName = orderName;
    }

    public void addOrder() {
        System.out.println("Order added successfully: " + orderName);
    }

    public abstract void showOrder();
}
