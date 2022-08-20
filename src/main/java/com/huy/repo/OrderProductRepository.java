package com.huy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huy.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer>{

}
