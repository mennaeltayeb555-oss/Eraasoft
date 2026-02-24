package org.example.oop.Problems.problem3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double r = sc.nextDouble();

        Circle c = new Circle(r);

        System.out.printf("%.9f", c.getArea());


    }
}
