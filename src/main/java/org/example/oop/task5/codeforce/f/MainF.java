package org.example.oop.task5.codeforce.f;

import java.util.Scanner;

public class MainF {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        MultiplicationTable obj = new MultiplicationTable(n);

        obj.printTable();
    }
}
