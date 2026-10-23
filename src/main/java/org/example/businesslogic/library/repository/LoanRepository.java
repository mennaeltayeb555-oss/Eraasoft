package org.example.businesslogic.library.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.library.model.Loan;
import org.example.businesslogic.library.model.LoanStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LoanRepository extends InMemoryRepository<Loan> {

    public LoanRepository() {
        // Hossam borrowed a book 20 days ago and didn't return it, so he has an overdue book
        LocalDate borrowDate = LocalDate.now().minusDays(20);
        save(new Loan(null, 3L, 1L, borrowDate, borrowDate.plusDays(14), null, 0, LoanStatus.BORROWED));
    }

    public List<Loan> findActiveByMemberId(Long memberId) {
        return findAll().stream()
                .filter(loan -> loan.getMemberId().equals(memberId))
                .filter(loan -> loan.getStatus() == LoanStatus.BORROWED)
                .toList();
    }
}
