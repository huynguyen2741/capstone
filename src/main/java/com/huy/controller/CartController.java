package com.huy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huy.repo.ProductRepository;
import com.huy.service.CartService;
import com.huy.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService service; 
	
//	add one item to cart
	@PostMapping("/add/{id}")
	public void addItem(@PathVariable int id) {
		try {
			service.addItem(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	get all cart info
	@GetMapping("/total")
	public String getCart() {
		return service.getCart();
	}
//	delete one item from cart
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable int id) {
		service.deleteItem(id);
	}

//	update the quantity for one item. 
	@PostMapping("add/{id}/{quantity}")
	public void updateQuantity(@PathVariable int id, @PathVariable int quantity) {
		service.updateItem(id,quantity);
	}

}
