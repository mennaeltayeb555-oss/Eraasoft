package org.example.oop.Problems.problem2;

import java.util.Scanner;

public class BasicDataType {
    int i;
    long l;
    char c;
    float f;
    double d;

    void readData() {
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        l = sc.nextLong();
        c = sc.next().charAt(0);
        f = sc.nextFloat();
        d = sc.nextDouble();
    }

    void printData() {
        System.out.println(i);
        System.out.println(l);
        System.out.println(c);
        System.out.println(f);
        System.out.println(d);
    }
}
