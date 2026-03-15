package org.example.oop.task5.hithub.fifteen;

public class MainFifteen {
    public static void main(String[] args) {

        Course c1 = new Course(1, "Java");
        Course c2 = new Course(2, "spring");

        Student s1 = new Student(10, "memma");

        s1.addCourse(c1);
        s1.addCourse(c2);

        System.out.println(s1.name);

        for (Course c : s1.courses) {
            System.out.println(c.courseName);
        }
    }
}
