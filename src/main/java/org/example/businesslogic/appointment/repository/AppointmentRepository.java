package org.example.businesslogic.appointment.repository;

import org.example.businesslogic.appointment.model.Appointment;
import org.example.businesslogic.appointment.model.AppointmentStatus;
import org.example.businesslogic.common.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AppointmentRepository extends InMemoryRepository<Appointment> {

    public List<Appointment> findActiveByDoctorId(Long doctorId) {
        return findAll().stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .filter(Appointment::isActive)
                .toList();
    }

    public long countActiveByPatientIdAndDate(Long patientId, LocalDate date) {
        return findAll().stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .filter(Appointment::isActive)
                .filter(a -> a.getStartTime().toLocalDate().equals(date))
                .count();
    }

    public boolean existsCanceled(Long doctorId, Long patientId, LocalDateTime startTime) {
        return findAll().stream()
                .anyMatch(a -> a.getStatus() == AppointmentStatus.CANCELED
                        && a.getDoctorId().equals(doctorId)
                        && a.getPatientId().equals(patientId)
                        && a.getStartTime().equals(startTime));
    }
}
