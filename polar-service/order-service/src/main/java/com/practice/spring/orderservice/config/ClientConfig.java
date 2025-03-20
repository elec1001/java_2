package com.practice.spring.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    WebClient webClient(
            ClientProperties clientProperties,
            WebClient.Builder webClientbuilder
    ) {
        return webClientbuilder
                .baseUrl(clientProperties.catalogServiceUri().toString())
                //@NotNull울 썼기 때문에 toString 써도 무방
                .build();
    }
}
