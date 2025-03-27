package com.practice.spring.webfrontsrevice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/webs")
public class OrderController {

    @GetMapping("/order")
    public String order() {
        return "order";
    }
}
