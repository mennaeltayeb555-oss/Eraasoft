package org.example.oop.task5.codeforce.y;

import java.util.Scanner;

public class MainY {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Fibonacci obj = new Fibonacci(n);

        obj.printSeries();
    }
}
