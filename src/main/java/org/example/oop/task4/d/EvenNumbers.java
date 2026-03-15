package org.example.oop.task4.d;

public class EvenNumbers {
    int n;

    EvenNumbers(int n){

        this.n = n;
    }

    public void printEven(){

        boolean found = false;
        for(int i = 1; i <= n; i++){

            if(i % 2 == 0){
                System.out.println(i);
                found = true;
            }
        }
        if(!found){
            System.out.println(-1);
        }

    }
}
