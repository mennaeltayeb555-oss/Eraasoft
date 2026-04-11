package org.example.exeptionhandler;

public class Task12NestedTryCatch {
    public static void main(String[] args) {

        try {

            try {
                int result = 10 / 0;
                System.out.println(result);
            } catch (NullPointerException e) {
                System.out.println("Inner catch: null error.");
            }

        } catch (ArithmeticException e) {
            System.out.println("Outer catch: arithmetic error.");
        }
    }
}
