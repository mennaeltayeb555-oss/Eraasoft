package org.example.oop.task5.codeforce.b;

import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        EvenNumbers obj = new EvenNumbers(n);
        obj.printEven();


    }
}
