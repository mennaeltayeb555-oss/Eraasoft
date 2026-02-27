package org.example.oop.task3.f;

public class LastDigitSum {
    private long n;
    private long m;

    public LastDigitSum(long n, long m) {
        this.n = n;
        this.m = m;
    }

    public long calculate() {
        long lastN = n % 10;
        long lastM = m % 10;
        return lastN + lastM;
    }
}

