package com.practice.spring.catalogservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApiController {

    @GetMapping("/")
    public String Home(){
        return "Hello World";
    }
}
