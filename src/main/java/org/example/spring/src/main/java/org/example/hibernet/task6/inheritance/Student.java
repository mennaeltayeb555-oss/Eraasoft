package org.example.hibernet.task6.inheritance;
import jakarta.persistence.Entity;

@Entity
public class Student extends Person {

    private String university;


    public Student() {
    }


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
