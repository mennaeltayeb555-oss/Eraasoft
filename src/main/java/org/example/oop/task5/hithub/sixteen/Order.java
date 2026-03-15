package org.example.oop.task5.hithub.sixteen;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private Person person;
    private ArrayList<Food> foods = new ArrayList<>();
    private Bill bill;
    private Gift gift;

    public Order(int orderId, Person person, Bill bill, Gift gift) {
        this.orderId = orderId;
        this.person = person;
        this.bill = bill;
        this.gift = gift;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public int getOrderId() {
        return orderId;
    }

    public Person getPerson() {
        return person;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public Bill getBill() {
        return bill;
    }

    public Gift getGift() {
        return gift;
    }
}
