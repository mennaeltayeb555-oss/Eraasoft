package org.example.businesslogic.appointment.repository;

import org.example.businesslogic.appointment.model.Doctor;
import org.example.businesslogic.common.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository extends InMemoryRepository<Doctor> {

    public DoctorRepository() {
        save(new Doctor(null, "Ahmed Ali", "Cardiology", true));
        save(new Doctor(null, "Sara Hassan", "Dermatology", true));
        save(new Doctor(null, "Omar Khaled", "Pediatrics", false));
    }
}
