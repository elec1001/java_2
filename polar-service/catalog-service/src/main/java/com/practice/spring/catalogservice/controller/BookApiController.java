package com.practice.spring.catalogservice.controller;

import com.practice.spring.catalogservice.domain.Book;
import com.practice.spring.catalogservice.domain.BookRepository;
import com.practice.spring.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookApiController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();

    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

}
