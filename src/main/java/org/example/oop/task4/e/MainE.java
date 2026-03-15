package org.example.oop.task4.e;

import java.util.Scanner;

public class MainE {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i =0; i< n;i++){
            arr[i] = sc.nextInt();
        }
        MaxFinder obj = new MaxFinder(arr);

        int result = obj.findMax();
        System.out.println(result);

    }
}
