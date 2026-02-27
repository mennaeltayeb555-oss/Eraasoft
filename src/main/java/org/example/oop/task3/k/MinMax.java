package org.example.oop.task3.k;

public class MinMax {
     int a;
     int b;
     int c;

    public MinMax(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public int getmin (){
        int min = a;
        if(b<min){
            min = b;
        }
        if(c<min){
            min = c;
        }
        return min;
    }
    public int getmax(){
        int max = a;
        if(b>max){
            max = b;
        }
        if(c>max){
            max = c;
        }
        return max;
    }


}
