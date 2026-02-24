package org.example.oop.task2.problem5;

class Student extends ShareData {

    private int age;

    public void setAge(int age) {
        if (age >7&& age< 30) {
            this.age = age;
        } else {
            System.out.println("Invalid age");
        }
    }

    public void printData() {
        System.out.println("Student:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Age: " + age);
    }
}