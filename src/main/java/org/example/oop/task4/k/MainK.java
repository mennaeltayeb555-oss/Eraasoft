package org.example.oop.task4.k;

import java.util.Scanner;

public class MainK {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("please Enter the number");
        int n=sc.nextInt();

        DivirsorsFinder obj = new DivirsorsFinder(n);
        obj.printDivisors();
    }
}