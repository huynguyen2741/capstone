package com.huy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huy.model.Address;
import com.huy.model.Product;
import com.huy.model.ProductCategory;
import com.huy.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping("/products")
	public List<Product> listAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping("/{id}")
	public Optional<Product> getProductById(int id){
		return productService.getProductById(id);		
	}
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
	@PostMapping("/add")
	public String addProduct(@RequestBody Product p) {
		Product prod= new Product();
		
		prod.setDescription(p.getDescription());
		prod.setImage_url(p.getImage_url());
		prod.setName(p.getName());
		prod.setPrice(p.getPrice());
		prod.setQuantity(p.getQuantity());
		prod.setWeight(p.getWeight());
		
//		prod.setProductCategory(p.getProductCategory());
		ProductCategory pc = p.getProductCategory();
//		p.getProductCategory().setProduct(p);
		pc.setProduct(p);
		prod.setProductCategory(pc);
		
		productService.addProduct(p);
		return "<h1>Product Added</h1>";
	}
	@PostMapping("/{id}")
	public void updateProductById(@PathVariable Integer id, @RequestBody Product p){
		p.setProductId(id);
		productService.updateProductById(p);
	}
}
