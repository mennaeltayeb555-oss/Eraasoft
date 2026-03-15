package org.example.oop.task4.n;

public class NumbersHistogram {
    char symbol;
    NumbersHistogram(char symbol){
      this.symbol=symbol;
    }
    public void printHistogram(int[] arr){

        for(int i = 0; i < arr.length; i++){

            for(int j = 0; j < arr[i]; j++){
                System.out.print(symbol);
            }

            System.out.println();
        }

    }

}
