package com.practice.spring.webfrontsrevice.controller;

import com.practice.spring.webfrontsrevice.dto.OrderClientReponseDTO;
import com.practice.spring.webfrontsrevice.dto.OrderRequestDTO;
import com.practice.spring.webfrontsrevice.dto.OrderResponseDTO;
import com.practice.spring.webfrontsrevice.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webs/api/order")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO createOrder(
            @RequestHeader(value =AUTHORIZATION, required = false) String accessToken,
            @RequestBody OrderRequestDTO orderRequestDTO) {

        OrderClientReponseDTO ordered=orderService.getOrder(accessToken, orderRequestDTO);
                return ordered.toOrderResponseDTO();
    }
}
