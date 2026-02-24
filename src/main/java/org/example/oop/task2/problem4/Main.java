package org.example.oop.task2.problem4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    //2 obj forboth
    Player p = new Player();
    Student s = new Student();
    //call setts
        System.out.println("Enter player data:");
        p.setId(sc.nextInt());
        p.setName(sc.next());
        p.setNumber(sc.nextInt());

        System.out.println("\nEnter student data:");
        s.setId(sc.nextInt());
        s.setName(sc.next());
        s.setAge(sc.nextInt());
    //print
        System.out.println("\n--- result ---");
        p.printData();
        System.out.println();
        s.printData();
}
}
