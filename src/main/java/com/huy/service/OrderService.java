package com.huy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huy.model.Order;
import com.huy.repo.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository oService;

	public List<Order> getOrders(){
		return oService.findAll();
	}
	
	
}
