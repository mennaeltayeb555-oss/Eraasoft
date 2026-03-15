package org.example.oop.task4.e;

public class MaxFinder {
    int[] arr;

    MaxFinder(int[] arr){
        this.arr = arr;
    }

    public int findMax(){

        int max= arr[0];

        for(int i = 1;i < arr.length; i++){

            if(arr[i]> max){
                max=arr[i];
            }
        }

        return max;
    }
}
