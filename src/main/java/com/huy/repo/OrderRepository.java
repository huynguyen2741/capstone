package com.huy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huy.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
