package org.example.oop.task3.H;

import java.util.Scanner;

public class MainH {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        TwoNumber obj = new TwoNumber(a, b);
        obj.calculate();
    }
}
