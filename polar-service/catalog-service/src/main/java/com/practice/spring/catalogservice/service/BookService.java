package com.practice.spring.catalogservice.service;

import com.practice.spring.catalogservice.domain.Book;
import com.practice.spring.catalogservice.domain.BookResponseDTO;
import com.practice.spring.catalogservice.domain.BookRepository;
import com.practice.spring.catalogservice.exception.BookAlreadyExistsException;
import com.practice.spring.catalogservice.exception.BookNotFoundExcetion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookResponseDTO> viewBookList() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(BookResponseDTO::from)
                .collect(Collectors.toList());

    }
    public Book viewBook(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(()->new BookNotFoundExcetion(isbn));
    }

    public Book addBookToCatalog(Book book) {
       if (bookRepository.existsByIsbn(book.isbn())){
           throw  new BookAlreadyExistsException(book.isbn());
       }

        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String  isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(
                        existingBook->{
                            Book.builder()
                                    .id(existingBook.id())
                                    .isbn(isbn)
                                    .title(book.title())
                                    .author(book.author())
                                    .price(book.price())
                                    .createdAt(existingBook.createdAt())
                                    .lastModifiedAt(existingBook.lastModifiedAt())
                                    .version(existingBook.version())
                                    .build();
                            return bookRepository.save(book);
                        }
                ).orElseGet(()->bookRepository.save(book));
    }
}
