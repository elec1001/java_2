package com.practice.spring.webfrontsrevice.dto;

import lombok.Getter;

@Getter
public class CreateCatalogRequestDTO {
    private String isbn;
    private String title;
    private String author;
    private Double price;
}
