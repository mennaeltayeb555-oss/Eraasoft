package org.example.oop.task5.hithub.eleven;

public class MainResturant {
    public static void main(String[] args){
        Resturant r1 = new ResturantCont("Pizza");
        Resturant r2 = new OrderCont("Burger");

        r1.addOrder();
        r1.showOrder();

        System.out.println("-----------");

        r2.addOrder();
        r2.showOrder();
    }
    }

