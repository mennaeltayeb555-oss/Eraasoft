package org.example.businesslogic.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.library.dto.BorrowRequest;
import org.example.businesslogic.library.model.Book;
import org.example.businesslogic.library.model.Loan;
import org.example.businesslogic.library.model.Member;
import org.example.businesslogic.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }

    @GetMapping("/members")
    public List<Member> getMembers() {
        return libraryService.getMembers();
    }

    @GetMapping("/loans")
    public List<Loan> getLoans() {
        return libraryService.getLoans();
    }

    @PostMapping("/loans")
    @ResponseStatus(HttpStatus.CREATED)
    public Loan borrow(@Valid @RequestBody BorrowRequest request) {
        return libraryService.borrow(request);
    }

    @PutMapping("/loans/{id}/return")
    public Loan returnBook(@PathVariable Long id) {
        return libraryService.returnBook(id);
    }
}
