package org.example.oop.task4.o;

import java.util.Scanner;

public class MainO {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();

        Pyramid pyramid = new Pyramid(n);
        pyramid.printStars();

    }

}
