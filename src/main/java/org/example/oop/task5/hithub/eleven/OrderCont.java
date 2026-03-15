package org.example.oop.task5.hithub.eleven;

public class OrderCont extends Resturant{
    public OrderCont(String orderName) {
        super(orderName);
    }

    @Override
    public void showOrder() {
        System.out.println("Showing order from Order Controller");
        System.out.println("Order: " + orderName);
    }
}
