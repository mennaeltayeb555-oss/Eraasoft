package org.example.task9.task2.email;

import jakarta.persistence.*;
import lombok.*;
import org.example.task9.task2.employee.Employee;

@Entity
@Table(name = "emails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;    // نوع الايميل (gmail, yahoo...)
    private String content; // الايميل الفعلي

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;
}
