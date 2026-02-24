package org.example.oop.task2.problem6;

public class Studentt {
    protected int id;
    protected String name;

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Invalid ID!");
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
