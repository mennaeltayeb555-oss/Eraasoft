package org.example.oop.task4.m;

import java.util.Scanner;

public class MainM {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    int a = sc.nextInt();
    int b = sc.nextInt();
    LuckycNumbers obj = new LuckycNumbers();

    boolean found = false;

        for(int i = a; i <= b; i++){

        if(obj.isLucky(i)){
            System.out.print(i + " ");
            found = true;
        }

    }
        if(!found){
        System.out.println(-1);
    }

}
}
