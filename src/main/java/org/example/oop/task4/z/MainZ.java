package org.example.oop.task4.z;

import java.util.Scanner;

public class MainZ {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int s = sc.nextInt();

        ThreeNumbers obj = new ThreeNumbers(k, s);

        System.out.println(obj.countWays());
    }
}
