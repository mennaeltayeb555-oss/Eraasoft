package org.example.oop.task5.hithub.sixteen;

import java.util.ArrayList;

public class Resturant {
    private String restaurantName;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();

    public Resturant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void showRestaurantData() {
        System.out.println("Restaurant: "+ restaurantName);

        System.out.println("Foods:");
        for (Food food : foods) {
            System.out.println(food.getName() + " - " + food.getPrice());
        }

        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println("Order Id: " + order.getOrderId());
            System.out.println("Person: " + order.getPerson().getName());

            for (Food food : order.getFoods()) {
                System.out.println("Food: " + food.getName());
            }

            System.out.println("Bill: " + order.getBill().getTotalPrice());

            if (order.getGift() != null) {
                System.out.println("Gift: " + order.getGift().getGiftName());
            }

            System.out.println("-----------");
        }
    }
}
