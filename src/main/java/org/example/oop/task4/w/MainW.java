package org.example.oop.task4.w;

import java.util.Scanner;

public class MainW {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Shape3 obj = new Shape3(n);
        obj.printDiamond();
    }
}
