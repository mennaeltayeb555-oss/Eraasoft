package org.example.oop.task4.k;

public class DivirsorsFinder {
    int n;

    DivirsorsFinder(int n){
        this.n = n;
    }

    public void printDivisors(){

        for(int i = 1; i <= n; i++){

            if(n % i == 0){
                System.out.println(i);
            }

        }

    }
}
