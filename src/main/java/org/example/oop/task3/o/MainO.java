package org.example.oop.task3.o;

import java.util.Scanner;

public class MainO {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int A = 0, B = 0;
        char operator = ' ';

        // Find operator position
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' ||
                    input.charAt(i) == '-' ||
                    input.charAt(i) == '*' ||
                    input.charAt(i) == '/') {

                operator = input.charAt(i);

                A = Integer.parseInt(input.substring(0, i));
                B = Integer.parseInt(input.substring(i + 1));

                break;
            }
        }
    }
}
