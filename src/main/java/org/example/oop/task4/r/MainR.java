package org.example.oop.task4.r;

import java.util.Scanner;

public class MainR {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SequencePrinter obj = new SequencePrinter();

        while (true) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n <= 0 || m <= 0) {
                break;
            }

            obj.printSequence(n, m);
        }

    }
}
