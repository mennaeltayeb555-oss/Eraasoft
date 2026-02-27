package org.example.oop.task3.m;

import java.util.Scanner;

public class MainM {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char x = sc.next().charAt(0);

        LetterChecker checker = new LetterChecker(x);
        checker.check();
    }
}
