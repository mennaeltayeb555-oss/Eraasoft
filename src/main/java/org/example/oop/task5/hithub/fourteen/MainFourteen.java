package org.example.oop.task5.hithub.fourteen;

public class MainFourteen {
    public static void main(String[] args) {

        Email e1 = new Email("ali@gmail.com");

        Student s1 = new Student(1, "Ali", e1);

        System.out.println(s1.name);
        System.out.println(s1.email.emailAddress);
    }
}
