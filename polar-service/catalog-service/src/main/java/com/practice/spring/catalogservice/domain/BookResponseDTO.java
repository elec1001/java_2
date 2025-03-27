package com.practice.spring.catalogservice.domain;

public record BookResponseDTO(
        String  isbn,
        String  title,
        String  author,
        Double  price
) {
    public static BookResponseDTO from(Book book) {
        return new BookResponseDTO(
                book.isbn(),
                book.title(),
                book.author(),
                book.price()
        );
    }
}
