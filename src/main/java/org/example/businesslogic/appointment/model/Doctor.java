package org.example.businesslogic.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements BaseEntity {

    private Long id;
    private String name;
    private String specialty;
    private boolean available;
}
