package org.example.oop.task5.codeforce.l;

public class GCDCalculator {
    int a;
    int b;

    public GCDCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int findGCD() {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;

        }

        return a;
    }
}
