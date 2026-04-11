package org.example.exeptionhandler;

public class Task9ExceptionPropagation {
    public static void method1() {
        method2();
    }

    public static void method2() {
        int result = 10 / 0;
        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            method1();
        } catch (ArithmeticException e) {
            System.out.println("Exception handled in main.");
        }
    }
}
