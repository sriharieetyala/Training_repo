package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Order1;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

  
    private OrderService service;

    @GetMapping("/hello")
    public String sayHello() {
        //log.debug("Logger added");
        return "hello";
    }

    @PostMapping("/order")
    public Order1 saveOrder(@Valid @RequestBody Order1 o) {
        service.insertOrder(o);
        return o;
    }
}
