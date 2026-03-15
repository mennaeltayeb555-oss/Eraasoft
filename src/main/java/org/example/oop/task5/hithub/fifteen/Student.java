package org.example.oop.task5.hithub.fifteen;

import java.util.ArrayList;

public class Student {
    int id;
    String name;

    ArrayList<Course> courses = new ArrayList<>();
    // Student has many Courses
    //Course has many Students

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCourse(Course c) {
        courses.add(c);
    }
}
