package org.example.businesslogic.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan implements BaseEntity {

    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    private LoanStatus status;

    public boolean isOverdue() {
        return status == LoanStatus.BORROWED && LocalDate.now().isAfter(dueDate);
    }
}
