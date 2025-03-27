package com.practice.spring.webfrontsrevice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCatalogResponseDTO {
    private int statusCode;
    private String message;
}
