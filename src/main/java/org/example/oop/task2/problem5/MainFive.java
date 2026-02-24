package org.example.oop.task2.problem5;

import java.util.Scanner;

public class MainFive {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Player p = new Player();

        Student s = new Student();

        Person person = new Person();
        // Person
        System.out.println("enter Person Data:");
        person.setId(sc.nextInt());
        person.setName(sc.next());
        // Player
        System.out.println("enter Player Data:");
        p.setId(sc.nextInt());
        p.setName(sc.next());
        p.setPhone(sc.next());
        p.setNumber(sc.nextInt());

        // Student
        System.out.println("enter Student Data:");
        s.setId(sc.nextInt());
        s.setName(sc.next());
        s.setPhone(sc.next());
        s.setAge(sc.nextInt());

        System.out.println("\n------");
        person.printData();
        System.out.println();
        p.printData();
        System.out.println();
        s.printData();
    }
}
