package org.example.oop.task5.codeforce.y;
//ask
public class Fibonacci {
    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    public void printSeries() {

        int a = 0;
        int b = 1;

        if (n >= 1) {
            System.out.print(a + " ");
        }
        if (n >= 2) {
            System.out.print(b + " ");
        }

        for (int i = 3; i <= n; i++) {

            int next = a + b;

            System.out.print(next + " ");

            a = b;
            b = next;
        }
    }
}
