package org.example.oop.task4.q;

public class Digits {
    public void printDigits(int n){

        if(n == 0){
            System.out.print(0);
            return;
        }

        while(n > 0){

            int digit = n % 10;

            System.out.print(digit + " ");

            n = n / 10;
        }

    }
}
