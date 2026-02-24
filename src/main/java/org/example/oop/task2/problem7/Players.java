package org.example.oop.task2.problem7;

public class Players {

    protected int id;
    protected String name;
    protected int number;

    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else
            System.out.println("Invalid ID");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void printBasicData() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Number: " + number);
    }
}
