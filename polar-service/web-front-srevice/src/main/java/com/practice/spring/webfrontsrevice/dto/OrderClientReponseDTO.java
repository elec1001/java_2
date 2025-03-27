package com.practice.spring.webfrontsrevice.dto;

import com.practice.spring.webfrontsrevice.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderClientReponseDTO {
    private Long id;
    private String bookIsbn;
    private String bookName;
    private String bookPrice;
    private Integer quantity;
    private OrderStatus status;
    private String message;

    public OrderResponseDTO toOrderResponseDTO (){

        return OrderResponseDTO.builder()
                .id(id)
                .bookIsbn(bookIsbn)
                .bookName(bookName)
                .bookPrice(bookPrice)
                .quantity(quantity)
                .status(status)
                .message("주문이 성공 하였습니다!")
                .build();

    }
}
