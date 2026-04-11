package org.example.exeptionhandler;

public class Task3NullPointerDemo {
    public static void printUpper(String str) {
        try {
            System.out.println(str.toUpperCase());
        } catch (NullPointerException e) {
            System.out.println("Error: string is null.");
        }
    }

    public static void main(String[] args) {
        printUpper(null);
    }
}
