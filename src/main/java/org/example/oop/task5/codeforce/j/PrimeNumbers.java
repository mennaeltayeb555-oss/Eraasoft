package org.example.oop.task5.codeforce.j;

public class PrimeNumbers {
    int n;

    public PrimeNumbers(int n) {
        this.n = n;
    }

    public boolean isPrime(int num) {

        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {

            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void printPrimes() {

        for (int i = 2; i <= n; i++) {

            if (isPrime(i)) {
                System.out.print(i + " ");
            }

        }

    }
}
