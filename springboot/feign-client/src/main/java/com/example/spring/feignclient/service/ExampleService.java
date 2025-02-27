package com.example.spring.feignclient.service;

import com.example.spring.feignclient.client.ExampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleClient exampleClient;

    //GET 호출 요청
    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }
}
