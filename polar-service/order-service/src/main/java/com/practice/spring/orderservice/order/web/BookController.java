package com.practice.spring.orderservice.order.web;

import com.practice.spring.orderservice.book.Book;
import com.practice.spring.orderservice.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public Flux<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public Mono<Book> enrollBook(@RequestBody Book book){
        return bookService.enrollBook(book);
    }

    @PutMapping("/{isbn}")
    public Mono<Book> updateBook(@PathVariable String isbn, @RequestBody Book book){
        return bookService.updateBook(isbn, book);
    }

    @DeleteMapping("/{isbn}")
    public Mono<Book> deleteBook(@PathVariable String isbn){
        return bookService.deleteBook(isbn);
    }


}
