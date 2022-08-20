package com.huy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huy.model.Product;

import com.huy.repo.ProductRepository;


@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	public Optional<Product> getProductById(int id){
		return repo.findById(id);		
	}
	
	public void deleteProduct(Integer id) {
		repo.deleteById(id);
	}
	
	public void addProduct(Product p) {
//		System.out.print("Before id: " + prod.getProductId());
		Product prod = repo.save(p);
		System.out.print("\n\n\nAfter id: " + prod.getProductId());
	}
	
	public void updateProductById(Product p ) {	
		repo.save(p);
	}
}
