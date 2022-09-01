package com.huy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huy.model.Product;
import com.huy.model.ProductCategory;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
	
	Optional<ProductCategory> findByCategory(String category);
}
