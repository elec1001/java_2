package com.practice.spring.orderservice.event;

public record OrderDispatchedMessage (
        Long orderId
){
}
