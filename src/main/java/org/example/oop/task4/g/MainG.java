package org.example.oop.task4.g;

import java.util.Scanner;

public class MainG {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Factorial obj = new Factorial();

        for(int i = 0; i < t; i++){

            int n = sc.nextInt();

            long result = obj.calculateFactorial(n);

            System.out.println(result);

        }

    }
}
