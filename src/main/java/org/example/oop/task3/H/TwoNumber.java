package org.example.oop.task3.H;

public class TwoNumber {
    private int a;
    private int b;

    public TwoNumber(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void calculate() {

        double result = (double) a / b;

        System.out.println("floor " + a + " / " + b + " = " + (long)Math.floor(result));
        System.out.println("ceil " + a + " / " + b + " = " + (long)Math.ceil(result));
        System.out.println("round " + a + " / " + b + " = " + Math.round(result));
    }
}
