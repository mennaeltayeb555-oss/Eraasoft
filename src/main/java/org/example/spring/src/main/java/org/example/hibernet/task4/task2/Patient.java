package org.example.hibernet.task4.task2;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String typeOfDisease;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToMany(mappedBy = "patients")
    private List<Hospital> hospitals = new ArrayList<>();

    public Patient() {
    }

    // Getters and Setters
}