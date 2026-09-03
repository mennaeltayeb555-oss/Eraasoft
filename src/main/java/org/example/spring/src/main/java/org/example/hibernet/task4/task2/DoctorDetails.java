package org.example.hibernet.task4.task2;
import jakarta.persistence.*;

@Entity
@Table(name = "doctor_details")
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullAddress;

    private String firstName;

    private String lastName;

    private int age;

    @OneToOne(mappedBy = "doctorDetails")
    private Doctor doctor;

    public DoctorDetails() {
    }

    // Getters and Setters
}
