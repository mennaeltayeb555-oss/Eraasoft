package org.example.oop.task3.g;

public class SumCalculator {
    private long n;

    public SumCalculator(long n) {
        this.n = n;
    }

    public long calculate() {
        return n * (n + 1) / 2;
    }
}
