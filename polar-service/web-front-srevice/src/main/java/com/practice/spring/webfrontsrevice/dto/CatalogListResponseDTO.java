package com.practice.spring.webfrontsrevice.dto;

import lombok.Getter;

@Getter
public class CatalogListResponseDTO {
    private String isbn;
    private String title;
    private String author;
    private Double price;
}
