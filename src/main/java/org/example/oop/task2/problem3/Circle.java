package org.example.oop.task2.problem3;

public class Circle {
    double radius;
    static final double PI = 3.141592653;

    // كونستركتور
    Circle(double radius) {
        this.radius = radius;
    }

    double getArea() {
        return PI * radius * radius;
    }
}
