package org.example.oop.task2.problem1;

public class SayHello {
    String name;

    // Constructor
    SayHello(String name) {
        this.name = name;
    }

    void printMessage() {
        System.out.println("Hello, " + name);
    }
}
