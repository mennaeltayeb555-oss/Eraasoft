package org.example.oop.task2.problem7;

import java.util.Scanner;

public class MainClup {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClupFcPlayer fc = new ClupFcPlayer();
        ClupRelPlayer rel = new ClupRelPlayer();

        System.out.println("Enter FC Player Data:");
        fc.setId(sc.nextInt());
        fc.setName(sc.next());
        fc.setNumber(sc.nextInt());
        fc.setFcode(sc.next());

        System.out.println("\nEnter REL Player Data:");
        rel.setId(sc.nextInt());
        rel.setName(sc.next());
        rel.setNumber(sc.nextInt());
        rel.setRcode(sc.next());

        System.out.println("\n--- Output ---");

        System.out.println("FC Player:");
        fc.printBasicData();

        System.out.println("\nREL Player:");
        rel.printFullData();
    }
}
