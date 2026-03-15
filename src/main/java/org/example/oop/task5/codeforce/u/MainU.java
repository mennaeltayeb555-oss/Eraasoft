package org.example.oop.task5.codeforce.u;

import java.util.Scanner;

public class MainU {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        SomeSums obj = new SomeSums(n, a, b);

        System.out.println(obj.calculate());
    }
}
