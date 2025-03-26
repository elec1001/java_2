package com.practice.spring.edgeservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Value("${polar.auth-service-url}")
    private String authServiceUrl;
}
