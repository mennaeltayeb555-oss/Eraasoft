package org.example.oop.task4.m;

public class LuckycNumbers {
    public boolean isLucky(int n){
        while(n > 0){

            int digit = n % 10;

            if(digit!= 4&& digit!= 7){
                return false;
            }
            n = n / 10;
        }

        return true;
    }

}
