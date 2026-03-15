package org.example.oop.task4.a;

import java.util.Scanner;

public class MainA {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("please Enter the number");
        int n = sc.nextInt();


        OnetoN obj = new OnetoN(n);

        obj.PrintNum();

    }


}
