package com.chubb.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chubb.repository.OrderRepositry;
import com.chubb.request.Order1;

@Service
public class OrderService {
	//purpose of this service to implement business logic
	//controller is just to handle requests
	@Autowired
	OrderRepositry orderRepository;
	public void insertOrder(Order1 order) {
		System.out.println(order);
	}

}
