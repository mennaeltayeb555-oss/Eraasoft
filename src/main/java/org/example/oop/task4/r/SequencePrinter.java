package org.example.oop.task4.r;

public class SequencePrinter {

    public void printSequence(int n, int m){
       //////
        int start = Math.min(n, m);
        int end = Math.max(n, m);
        int sum = 0;
        //////

        for(int i = start; i <= end; i++){

            System.out.print(i + " ");

            sum += i;
        }

        System.out.println("sum =" + sum);
    }

}
