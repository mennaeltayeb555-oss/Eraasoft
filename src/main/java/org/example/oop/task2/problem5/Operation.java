package org.example.oop.task2.problem5;

public class Operation {
    int x, y;

    Operation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void print() {
        System.out.println(x + " + " + y + " = " + (x + y));
        System.out.println(x + " * " + y + " = " + (x * y));
        System.out.println(x + " - " + y + " = " + (x - y));
    }
}
