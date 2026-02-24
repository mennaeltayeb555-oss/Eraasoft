package org.example.oop.task2.problem2.Problem3;

import java.util.Scanner;

public class SumEvenMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SumEven obj = new SumEven();

        System.out.print("enter first number: ");
        obj.setN1(sc.nextInt());

        System.out.print("enter second number: ");
        obj.setN2(sc.nextInt());

        System.out.print("enter third number: ");
        obj.setN3(sc.nextInt());

        obj.printSum();
    }
}
