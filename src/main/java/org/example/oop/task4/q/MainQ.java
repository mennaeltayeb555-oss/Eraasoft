package org.example.oop.task4.q;

import java.util.Scanner;

public class MainQ {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Digits obj = new Digits();
        //////////////////////////////////////////
        for(int i = 0; i < t; i++){

            int n = sc.nextInt();

            obj.printDigits(n);

            System.out.println();
        }

    }
}
