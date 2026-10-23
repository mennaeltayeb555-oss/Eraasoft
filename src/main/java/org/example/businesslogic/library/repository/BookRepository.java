package org.example.businesslogic.library.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends InMemoryRepository<Book> {

    public BookRepository() {
        save(new Book(null, "Clean Code", "Robert Martin", 3));
        save(new Book(null, "Effective Java", "Joshua Bloch", 2));
        save(new Book(null, "Head First Java", "Kathy Sierra", 5));
        save(new Book(null, "Spring in Action", "Craig Walls", 0));
        save(new Book(null, "Java Concurrency in Practice", "Brian Goetz", 4));
    }
}
