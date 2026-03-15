package org.example.oop.task5.codeforce.s;

import java.util.Scanner;

public class MainS {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        OddSum obj = new OddSum();

        for (int i = 0; i < t; i++) {

            int x = sc.nextInt();
            int y = sc.nextInt();

            System.out.println(obj.calculateSum(x, y));
        }
    }
}
