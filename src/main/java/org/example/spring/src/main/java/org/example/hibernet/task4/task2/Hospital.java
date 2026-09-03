package org.example.hibernet.task4.task2;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int numberOfDoctors;

    private int numberOfPatient;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "hospital_patient",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients = new ArrayList<>();

    public Hospital() {
    }

    // Getters and Setters
}
