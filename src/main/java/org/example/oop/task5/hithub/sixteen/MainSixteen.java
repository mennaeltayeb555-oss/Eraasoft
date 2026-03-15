package org.example.oop.task5.hithub.sixteen;

public class MainSixteen {
    public static void main(String[] args) {

        Resturant restaurant = new Resturant("il nilo");

        Food f1 = new Food(1, "Pizza", 150);
        Food f2 = new Food(2, "pasta", 100);

        restaurant.addFood(f1);
        restaurant.addFood(f2);

        Person p1 = new Person(1, "Menna");

        Bill b1 = new Bill(250);
        Gift g1 = new Gift("Free Juice");

        Order o1 = new Order(101, p1, b1, g1);
        o1.addFood(f1);
        o1.addFood(f2);

        restaurant.addOrder(o1);

        restaurant.showRestaurantData();
    }
}
