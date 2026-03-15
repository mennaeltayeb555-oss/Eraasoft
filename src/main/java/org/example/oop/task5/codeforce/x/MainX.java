package org.example.oop.task5.codeforce.x;

public class MainX {
    public int calculate(int n){

        int ones = 0;

        while(n > 0){

            if(n % 2 == 1){
                ones++;
            }

            n /= 2;
        }
        return (int)Math.pow(2, ones) - 1;
    }
}
