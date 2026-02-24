package org.example.oop.task2.problem4;

class Student {

    private int id;
    private String name;
    private int age;

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

    public void setAge(int age) {
        if (age > 7 && age < 30) {
            this.age = age;
        } else {
            System.out.println("ERROR Age,it Must be between( 8 and 29)");
        }
    }

    public void printData() {
        System.out.println("Student Data:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}