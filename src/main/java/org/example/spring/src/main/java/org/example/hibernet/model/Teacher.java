package org.example.hibernet.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "teacher")
@Check(constraints = "age BETWEEN 15 AND 20")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "address", unique = true)
    private String address;

    public Teacher() {
    }

    public Teacher(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + "', age=" + age + ", address='" + address + "'}";
    }
}