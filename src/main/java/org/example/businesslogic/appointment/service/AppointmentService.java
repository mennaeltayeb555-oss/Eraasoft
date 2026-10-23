package org.example.businesslogic.appointment.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.appointment.dto.BookAppointmentRequest;
import org.example.businesslogic.appointment.model.Appointment;
import org.example.businesslogic.appointment.model.AppointmentStatus;
import org.example.businesslogic.appointment.model.Doctor;
import org.example.businesslogic.appointment.repository.AppointmentRepository;
import org.example.businesslogic.appointment.repository.DoctorRepository;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private static final int MAX_APPOINTMENTS_PER_DAY = 2;
    private static final int CANCEL_BEFORE_HOURS = 2;

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public synchronized Appointment book(BookAppointmentRequest request) {
        Doctor doctor = doctorRepository.findById(request.doctorId())
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        if (!doctor.isAvailable()) {
            throw new BusinessException("Doctor " + doctor.getName() + " is not available for booking");
        }

        LocalDateTime start = request.startTime();
        LocalDateTime end = start.plusMinutes(request.serviceType().getDurationMinutes());

        checkSlotWasNotCanceled(request);
        checkDoctorIsFree(doctor.getId(), start, end);
        checkPatientDailyLimit(request.patientId(), start.toLocalDate());

        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctor.getId());
        appointment.setPatientId(request.patientId());
        appointment.setServiceType(request.serviceType());
        appointment.setStartTime(start);
        appointment.setEndTime(end);
        appointment.setStatus(AppointmentStatus.BOOKED);
        return appointmentRepository.save(appointment);
    }

    public Appointment cancel(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.CANCELED) {
            throw new BusinessException("Appointment is already canceled");
        }

        LocalDateTime lastCancelTime = appointment.getStartTime().minusHours(CANCEL_BEFORE_HOURS);
        if (LocalDateTime.now().isAfter(lastCancelTime)) {
            throw new BusinessException("You can only cancel at least " + CANCEL_BEFORE_HOURS + " hours before the appointment");
        }

        appointment.setStatus(AppointmentStatus.CANCELED);
        return appointmentRepository.save(appointment);
    }

    // the patient can't take back the same slot after canceling it
    private void checkSlotWasNotCanceled(BookAppointmentRequest request) {
        boolean canceledBefore = appointmentRepository.existsCanceled(
                request.doctorId(), request.patientId(), request.startTime());
        if (canceledBefore) {
            throw new BusinessException("This appointment was canceled before and cannot be booked again");
        }
    }

    private void checkDoctorIsFree(Long doctorId, LocalDateTime start, LocalDateTime end) {
        for (Appointment other : appointmentRepository.findActiveByDoctorId(doctorId)) {
            boolean overlaps = start.isBefore(other.getEndTime()) && end.isAfter(other.getStartTime());
            if (overlaps) {
                throw new BusinessException("Doctor already has an appointment from "
                        + other.getStartTime() + " to " + other.getEndTime());
            }
        }
    }

    private void checkPatientDailyLimit(Long patientId, LocalDate date) {
        long count = appointmentRepository.countActiveByPatientIdAndDate(patientId, date);
        if (count >= MAX_APPOINTMENTS_PER_DAY) {
            throw new BusinessException("Patient cannot book more than " + MAX_APPOINTMENTS_PER_DAY + " appointments on the same day");
        }
    }
}
