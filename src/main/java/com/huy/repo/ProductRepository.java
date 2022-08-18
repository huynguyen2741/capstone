package com.huy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huy.model.Product;


public interface ProductRepository extends JpaRepository<Product,Integer> {
	Optional<Product> findById(int id);
}
