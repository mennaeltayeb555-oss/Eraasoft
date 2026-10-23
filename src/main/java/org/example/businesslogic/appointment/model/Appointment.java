package org.example.businesslogic.appointment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Appointment implements BaseEntity {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private ServiceType serviceType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;

    public boolean isActive() {
        return status == AppointmentStatus.BOOKED;
    }
}
