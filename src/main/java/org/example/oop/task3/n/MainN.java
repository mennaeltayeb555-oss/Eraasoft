package org.example.oop.task3.n;

import java.util.Scanner;

public class MainN {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char x = sc.next().charAt(0);

        Char obj = new Char(x);
        System.out.println(obj.convert());
        sc.close();
    }
}
