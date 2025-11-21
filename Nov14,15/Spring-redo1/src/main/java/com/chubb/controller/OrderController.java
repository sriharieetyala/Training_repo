package com.chubb.controller;


import com.chubb.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.request.Order1;
import com.chubb.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
//this converts this class into a spring bean

public class OrderController {
	@Autowired
	OrderService service;
	@GetMapping("/order") //this is a unique path
	String getOrder() {
		return "hello";
	}
	@PostMapping("/order")
	String saveOrder(@Valid @RequestBody Order1 order) {
		
		//log.debug("logger added");
		//log.info("Order received: {}", order);
		service.insertOrder(order);
		float x=order.getPrice()*order.getQuantity();
		String house = order.getAddress().getHouse();
		String pin = order.getAddress().getPin();
		return "Order saved with total price: "+x+" to be delivered at "+house+" , "+pin;
	}

}