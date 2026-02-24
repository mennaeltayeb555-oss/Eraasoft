package org.example.oop.task2.problem6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PrivateSchoolStudent privateStudent = new PrivateSchoolStudent();
        PublicSchoolStudent publicStudent = new PublicSchoolStudent();
        //fristclass
        System.out.println("enter private school student data:");
        privateStudent.setId(sc.nextInt());
        privateStudent.setName(sc.next());
        //secondclass
        System.out.println("enter public school student daata:");
        publicStudent.setId(sc.nextInt());
        publicStudent.setName(sc.next());

        System.out.println("\n--- result ---");
        privateStudent.printData();
        System.out.println();
        publicStudent.printData();
    }
}
