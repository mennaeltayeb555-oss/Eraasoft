package org.example.oop.task4.n;

import java.util.Scanner;

public class MainN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char s = sc.next().charAt(0);
        int n = sc.nextInt();
        //////////////////////////////////////
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        NumbersHistogram obj = new NumbersHistogram(s);

        obj.printHistogram(arr);

    }
}
