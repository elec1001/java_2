package com.practice.spring.orderservice.order.domain;

public record OrderResponseDTO(
        String bookIsbn,
        String bookName,
        Integer quantity,
        OrderStatus status
) {
    public static OrderResponseDTO from(Order order) {
        return new OrderResponseDTO(
                order.bookIsbn(),
                order.bookName(),
                order.quantity(),
                order.status()
        );
    }
}
