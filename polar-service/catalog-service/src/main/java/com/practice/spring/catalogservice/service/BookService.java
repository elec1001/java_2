package com.practice.spring.catalogservice.service;

import com.practice.spring.catalogservice.domain.Book;
import com.practice.spring.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }
}
