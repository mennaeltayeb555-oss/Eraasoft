package org.example.oop.task5.codeforce.c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        EvenPositive obj = new EvenPositive(arr);
        obj.countNumbers();
    }
}

