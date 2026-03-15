package org.example.oop.task4.d;

import java.util.Scanner;

public class MainD {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("please Enter the number");
        int n = sc.nextInt();

        EvenNumbers obj = new EvenNumbers(n);

        obj.printEven();

    }

}
