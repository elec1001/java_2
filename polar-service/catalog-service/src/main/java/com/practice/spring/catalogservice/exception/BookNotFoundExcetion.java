package com.practice.spring.catalogservice.exception;

public class BookNotFoundExcetion extends RuntimeException{
    public BookNotFoundExcetion(String isbn){
        super("Book not found: "+isbn);
    }
}
