package org.example.oop.task5.hithub.ten;

public abstract class BaseSchool {
    protected String studentName;

    public BaseSchool(String studentName) {
        this.studentName = studentName;
    }

    public void addStudent() {
        System.out.println("Student added: " + studentName);
    }

    public void showStudent() {
        System.out.println("Student name: " + studentName);
    }
}
