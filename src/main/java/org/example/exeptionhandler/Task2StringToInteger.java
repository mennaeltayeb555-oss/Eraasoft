package org.example.exeptionhandler;

import java.util.Scanner;

public class Task2StringToInteger {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            String str = input.nextLine();

            int num = Integer.parseInt(str);
            System.out.println("Number = " + num);

        } catch (NumberFormatException e) {
            System.out.println("Error: invalid number format.");
        }

        input.close();
    }
}
