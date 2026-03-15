package org.example.oop.task4.i;

import java.util.Scanner;

public class MainI {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PalindromeChecker obj = new PalindromeChecker(n);

        obj.checkPalindrome();

    }
}
