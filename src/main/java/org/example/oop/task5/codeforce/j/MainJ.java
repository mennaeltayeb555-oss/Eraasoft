package org.example.oop.task5.codeforce.j;

import java.util.Scanner;

public class MainJ {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        PrimeNumbers obj = new PrimeNumbers(n);

        obj.printPrimes();

    }
}
