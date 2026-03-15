package org.example.oop.task5.hithub.ten;

public class ShowStudentImpl extends BaseSchool {
    public ShowStudentImpl(String studentName) {
        super(studentName);
    }

    @Override
    public void showStudent() {
        System.out.println("You are on show student");
        System.out.println("Student name: " +studentName);
    }
}

