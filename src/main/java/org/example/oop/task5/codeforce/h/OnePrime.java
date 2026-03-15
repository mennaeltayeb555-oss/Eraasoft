package org.example.oop.task5.codeforce.h;

public class OnePrime {
    int x;

    public OnePrime(int x) {
        this.x = x;
    }

    public boolean isPrime() {

        for (int i=2;i <=Math.sqrt(x); i++) {

            if (x % i == 0) {
                return false;
            }

        }

        return true;
    }
}
