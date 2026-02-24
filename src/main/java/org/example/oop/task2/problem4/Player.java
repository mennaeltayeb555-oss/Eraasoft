package org.example.oop.task2.problem4;

class Player {

    private int id;
    private String name;
    private int number;

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("id must be greater than 0");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        if (number >= 0 && number <= 99) {
            this.number = number;
        } else {
            System.out.println("ERROR Player Number it Must be between( 0 and 99)");
        }
    }

    public void printData() {
        System.out.println("Player Data:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Number: " + number);
    }
}
