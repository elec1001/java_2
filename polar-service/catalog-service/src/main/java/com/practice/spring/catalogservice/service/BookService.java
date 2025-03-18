package com.practice.spring.catalogservice.service;

import com.practice.spring.catalogservice.domain.Book;
import com.practice.spring.catalogservice.domain.BookRepository;
import com.practice.spring.catalogservice.exception.BookAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book addBookToCatalog(Book book) {
       if (bookRepository.existsByIsbn(book.isbn())){
           throw  new BookAlreadyExistsException(book.isbn());
       }

        return bookRepository.save(book);
    }
}
