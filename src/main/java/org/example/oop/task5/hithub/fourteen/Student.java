package org.example.oop.task5.hithub.fourteen;

public class Student {
    int id;
    String name;

    Email email;   // student has-A email relation

    public Student(int id, String name, Email email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
