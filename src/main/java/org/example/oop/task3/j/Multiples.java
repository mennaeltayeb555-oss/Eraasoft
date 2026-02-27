package org.example.oop.task3.j;

public class Multiples {
    private int a;
    private int b;

    public Multiples(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public boolean isMultiples() {
        return (a % b==0||b% a == 0);
    }
}
