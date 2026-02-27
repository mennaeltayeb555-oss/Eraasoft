package org.example.oop.task3.o;

public class Calculator {
    private int A;
    private int B;
    private char operator;

    // Constructor
    public Calculator(int A, char operator, int B) {
        this.A = A;
        this.B = B;
        this.operator = operator;
    }

    // Method to calculate result
    public int calculate() {
        switch (operator) {
            case '+':
                return A + B;
            case '-':
                return A - B;
            case '*':
                return A * B;
            case '/':
                return A / B;   //
            default:
                return 0;
        }
    }
}
