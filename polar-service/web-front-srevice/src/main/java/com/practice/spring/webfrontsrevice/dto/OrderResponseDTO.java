package com.practice.spring.webfrontsrevice.dto;

import com.practice.spring.webfrontsrevice.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDTO {
    private Long id;
    private String bookIsbn;
    private String bookName;
    private String bookPrice;
    Integer quantity;
    OrderStatus status;
    String message;


}
