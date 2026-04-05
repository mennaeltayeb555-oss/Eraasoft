package org.example.equalsandhashcode;

public class Student {
    int id;
    String email;

    public Student(int id, String email) {
        this.id = id;
        this.email = email;
    }
    //based on id
    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return this.id == s.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    //based on email :
    /*@Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return this.email.equals(s.email);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }*/
}
