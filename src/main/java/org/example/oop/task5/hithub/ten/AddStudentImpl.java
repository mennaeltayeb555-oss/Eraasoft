package org.example.oop.task5.hithub.ten;

public class AddStudentImpl extends BaseSchool{
    public AddStudentImpl(String studentName) {
        super(studentName);
    }

    @Override
    public void addStudent() {
        System.out.println("You are on add student");
        System.out.println("Student added: " + studentName);
    }
}
