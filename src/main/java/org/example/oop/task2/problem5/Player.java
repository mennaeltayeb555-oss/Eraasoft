package org.example.oop.task2.problem5;

class Player extends ShareData {

    private int number;

    public void setNumber(int number) {
        if (number>=0 &&number<= 99) {
            this.number = number;
        } else {
            System.out.println("Invalid number");
        }
    }

    public void printData() {
        System.out.println("Player:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Number: " + number);
    }
}