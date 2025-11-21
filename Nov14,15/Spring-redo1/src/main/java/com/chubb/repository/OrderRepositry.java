package com.chubb.repository;

import org.springframework.data.repository.CrudRepository;

import com.chubb.request.Order1;

public interface OrderRepositry extends CrudRepository<Order1, Integer>{

}
