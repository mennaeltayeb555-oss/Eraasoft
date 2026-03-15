package org.example.oop.task5.codeforce.h;

import java.util.Scanner;

public class MianH {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        OnePrime obj = new OnePrime(x);
//
        if (obj.isPrime()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}
