package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.Order1;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    public void insertOrder(Order1 order) {
        // Lombok @Slf4j gives you "log"
        //log.debug("Inserting order: {}", order);
        System.out.println(order);
    }
}
