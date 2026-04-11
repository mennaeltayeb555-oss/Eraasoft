package org.example.exeptionhandler;

import java.util.Scanner;

public class Task1DivideNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            int num1 = input.nextInt();

            System.out.print("Enter second number: ");
            int num2 = input.nextInt();

            int result = num1 / num2;
            System.out.println("Result = " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: cannot divide by zero.");
        }

        input.close();
    }
}
