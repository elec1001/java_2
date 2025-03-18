package com.practice.spring.catalogservice.controller;

import com.practice.spring.catalogservice.domain.Book;
import com.practice.spring.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookApiController {

    private final BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();

    }

}
