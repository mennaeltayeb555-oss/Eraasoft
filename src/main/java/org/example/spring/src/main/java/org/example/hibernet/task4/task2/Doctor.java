package org.example.hibernet.task4.task2;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private double salary;

    @OneToOne
    @JoinColumn(name = "doctor_details_id")
    private DoctorDetails doctorDetails;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor")
    private List<Patient> patients = new ArrayList<>();

    public Doctor() {
    }

    // Getters and Setters
}
//Doctor
//  │
//  ├── 1:1 → DoctorDetails
//  │
//  ├── N:1 → Hospital
//  │
//  └── 1:N → Patient
