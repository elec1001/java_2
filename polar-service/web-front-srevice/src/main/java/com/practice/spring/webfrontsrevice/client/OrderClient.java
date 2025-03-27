package com.practice.spring.webfrontsrevice.client;

import com.practice.spring.webfrontsrevice.dto.OrderClientReponseDTO;
import com.practice.spring.webfrontsrevice.dto.OrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="orderClient",url = "${polar.edge-service-url}/orders")
public interface OrderClient {

    @PostMapping
    OrderClientReponseDTO createOrder(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody OrderRequestDTO orderRequest);

}
