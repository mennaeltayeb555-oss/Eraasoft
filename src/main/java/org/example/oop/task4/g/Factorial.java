package org.example.oop.task4.g;

public class Factorial {
    public long calculateFactorial(int n){

        long fact = 1;

        for(int i = 1; i <= n; i++){
            fact *= i;
        }

        return fact;
    }
}
