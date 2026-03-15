package org.example.oop.task5.hithub.eleven;

public class ResturantCont extends Resturant{
    public ResturantCont(String orderName) {
        super(orderName);
    }

    @Override
    public void showOrder() {
        System.out.println("Showing order from Restaurant Controller");
        System.out.println("Order: " + orderName);
    }
}
