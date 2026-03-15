package org.example.oop.task5.codeforce.l;

import java.util.Scanner;

public class MainL {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        GCDCalculator obj = new GCDCalculator(a, b);

        System.out.println(obj.findGCD());
    }
}
