package org.example.oop.task3.g;

import java.util.Scanner;

public class MainG {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        SumCalculator obj = new SumCalculator(n);
        System.out.println(obj.calculate());
    }
}
