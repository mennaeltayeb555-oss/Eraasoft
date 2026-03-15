package org.example.oop.task5.codeforce.V;

import java.util.Scanner;

public class MainV {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Pum obj = new Pum(n);

        obj.printGame();
    }
}
