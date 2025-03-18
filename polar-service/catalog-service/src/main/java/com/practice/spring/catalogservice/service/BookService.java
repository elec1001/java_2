package com.practice.spring.catalogservice.service;

import com.practice.spring.catalogservice.domain.Book;
import com.practice.spring.catalogservice.domain.BookRepository;
import com.practice.spring.catalogservice.exception.BookAlreadyExistsException;
import com.practice.spring.catalogservice.exception.BookNotFoundExcetion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
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
                                    .createdAt(book.createdAt())
                                    .lastModifiedAt(book.lastModifiedAt())
                                    .version(existingBook.version())
                                    .build();
                            return bookRepository.save(existingBook);
                        }
                ).orElseGet(()->bookRepository.save(book));
    }
}
