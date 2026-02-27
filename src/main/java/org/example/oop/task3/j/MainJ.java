package org.example.oop.task3.j;

import java.util.Scanner;

public class MainJ {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        Multiples checker = new Multiples(A,B);

        if (checker.isMultiples()) {
            System.out.println("Multiples");
        } else {
            System.out.println("No Multiples");
        }
    }

}
