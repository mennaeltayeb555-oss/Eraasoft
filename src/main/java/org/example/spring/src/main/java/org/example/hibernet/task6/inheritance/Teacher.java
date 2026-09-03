package org.example.hibernet.task6.inheritance;
import jakarta.persistence.Entity;

@Entity
public class Teacher extends Person {

    private String subject;


    public Teacher() {
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
