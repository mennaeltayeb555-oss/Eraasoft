package org.example.oop.Problems.problem1;

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
