package org.example.oop.task2.problem2.problemtwo2;

import java.util.Scanner;

public class TeacherMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Teacher t = new Teacher();

        System.out.print("Enter ID: ");
        Long id = sc.nextLong();
        t.setId(id);

        System.out.print("Enter Name: ");
        String name = sc.next();
        t.setName(name);

        System.out.print("Enter Age: ");
        float age = sc.nextFloat();
        t.setAge(age);

        System.out.print("Enter Phone Number: ");
        String phone = sc.next();
        t.setPhoneNumber(phone);

        System.out.print("Enter Salary: ");
        float salary = sc.nextFloat();
        t.setSalary(salary);

        System.out.println("\n--- Teacher Data ---");
        System.out.println("ID: " + t.getId());
        System.out.println("Name: " + t.getName());
        System.out.println("Age: " + t.getAge());
        System.out.println("Phone: " + t.getPhoneNumber());
        System.out.println("Salary: " + t.getSalary());
    }
}
