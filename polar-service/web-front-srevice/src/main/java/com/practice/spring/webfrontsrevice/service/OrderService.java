package com.practice.spring.webfrontsrevice.service;

import com.practice.spring.webfrontsrevice.client.OrderClient;
import com.practice.spring.webfrontsrevice.dto.OrderClientReponseDTO;
import com.practice.spring.webfrontsrevice.dto.OrderRequestDTO;
import com.practice.spring.webfrontsrevice.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;

    public OrderClientReponseDTO getOrder(String accessToken,
                                                      OrderRequestDTO orderRequestDTO) {
        return orderClient.createOrder(accessToken,orderRequestDTO);
    }
}
