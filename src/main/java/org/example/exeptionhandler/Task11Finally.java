package org.example.exeptionhandler;

public class Task11Finally {
    public static void main(String[] args) {

        try {
            int result = 10 / 0;
            System.out.println(result);

        } catch (ArithmeticException e) {
            System.out.println("Error: cannot divide by zero.");

        } finally {
            System.out.println("Finally block always executes.");
        }
    }
}
//finally:
// بتشتغل دايمًا
// حتى لو مفيش error او مفيش

