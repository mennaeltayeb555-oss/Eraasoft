package org.example.businesslogic.library.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.library.dto.BorrowRequest;
import org.example.businesslogic.library.model.Book;
import org.example.businesslogic.library.model.Loan;
import org.example.businesslogic.library.model.LoanStatus;
import org.example.businesslogic.library.model.Member;
import org.example.businesslogic.library.repository.BookRepository;
import org.example.businesslogic.library.repository.LoanRepository;
import org.example.businesslogic.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private static final int MAX_BOOKS_PER_MEMBER = 3;

    // fine per day: first week is cheaper, after that it gets more expensive
    private static final int FIRST_WEEK_DAYS = 7;
    private static final double FIRST_WEEK_FINE_PER_DAY = 5;
    private static final double AFTER_FIRST_WEEK_FINE_PER_DAY = 10;

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public synchronized Loan borrow(BorrowRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundException("Member not found"));
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new NotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new BusinessException("No copies available for " + book.getTitle());
        }

        List<Loan> activeLoans = loanRepository.findActiveByMemberId(member.getId());

        boolean hasOverdueBook = activeLoans.stream().anyMatch(Loan::isOverdue);
        if (hasOverdueBook) {
            throw new BusinessException("Member has overdue books, return them first");
        }
        if (activeLoans.size() >= MAX_BOOKS_PER_MEMBER) {
            throw new BusinessException("Member cannot borrow more than " + MAX_BOOKS_PER_MEMBER + " books");
        }

        LocalDate today = LocalDate.now();
        Loan loan = new Loan();
        loan.setMemberId(member.getId());
        loan.setBookId(book.getId());
        loan.setBorrowDate(today);
        loan.setDueDate(today.plusDays(member.getType().getBorrowDays()));
        loan.setStatus(LoanStatus.BORROWED);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return loanRepository.save(loan);
    }

    public synchronized Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundException("Loan not found"));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new BusinessException("This book is already returned");
        }

        LocalDate today = LocalDate.now();
        long overdueDays = ChronoUnit.DAYS.between(loan.getDueDate(), today);

        loan.setReturnDate(today);
        loan.setFine(calculateFine(overdueDays));
        loan.setStatus(LoanStatus.RETURNED);

        Book book = bookRepository.findById(loan.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    private double calculateFine(long overdueDays) {
        if (overdueDays <= 0) {
            return 0;
        }
        if (overdueDays <= FIRST_WEEK_DAYS) {
            return overdueDays * FIRST_WEEK_FINE_PER_DAY;
        }
        long extraDays = overdueDays - FIRST_WEEK_DAYS;
        return FIRST_WEEK_DAYS * FIRST_WEEK_FINE_PER_DAY + extraDays * AFTER_FIRST_WEEK_FINE_PER_DAY;
    }
}
