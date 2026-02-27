package org.example.oop.task3.f;

import java.util.Scanner;

public class MainF {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        long m = sc.nextLong();

        LastDigitSum obj = new LastDigitSum(n, m);

        System.out.println(obj.calculate());
    }

}
