package com.huy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huy.model.Cart;
import com.huy.model.CartItem;
import com.huy.model.Product;
import com.huy.repo.ProductRepository;

@Service
public class CartService {

	@Autowired
	private ProductRepository pRepo;
	
	private static Cart c = new Cart();
	
	
//	add one item to the cart
	public void addItem(int id) throws Exception {
		Product p = pRepo.findById(id).get();
		if (p.getQuantity()<= 0) {
			throw new Exception("No Stock");
		}

		if (!validate(c,p)) {
//			extract attribute from p to populate CartItem
			CartItem ci = new CartItem();
			ci.setId(p.getProductId());
			ci.setInventory(p.getQuantity());
			ci.setPrice(p.getPrice());
			ci.setQuantity(1);
			
//			add the new item to Cart
			List<CartItem> list = c.getItems();
			list.add(ci);
			c.setItems(list);
//			decrease inventory
			p.setQuantity(p.getQuantity() - 1);
			pRepo.save(p);
		}	
	}
		
//	check if the Cart has the item or not.
	public boolean validate(Cart c, Product p) {
		List<CartItem> list = c.getItems();
		for(CartItem l:list) {
			if (l.getId() == p.getProductId()) {
				return true;
			}
		}
		
		return false;
		
	}
	
//	delete the item from cart
	public String deleteItem(int id) {
		Product p = pRepo.findById(id).get();
//		If cart does not have item then return
		if(!validate(c,p)) {
			return "<h1>none</h1>";			
		}		
//		Iterate to find and remove item
		List<CartItem>list = c.getItems();
		for(CartItem l:list) {
			if (l.getId() == p.getProductId()) {
				list.remove(l);
				break;
			}
		}
//		reset the grand total to get new grand total.
		c.setCartTotal(0);
		c.setItems(list);
		return "<h1>Delete complete</h1>";		
	}
	

//	get cart info
	public String getCart() {
//		reset the grand total to get new grand total.
		c.setCartTotal(0);
		return c.toString();
	}
	
	
	public void updateItem(int id, int quantity) {
//		check if inventory is enough
		Product p = pRepo.findById(id).get();
		if(!validate(c,p) || quantity > p.getQuantity()) {
			return;			
		}	
//		increase quantity and decrease inventory
		CartItem i = c.getItems().stream().filter((item)-> item.getId() == p.getProductId()).findFirst().get();
		i.setQuantity(i.getQuantity() + quantity);
//		decrease the inventory
		p.setQuantity(p.getQuantity() - quantity);
		pRepo.save(p);
	}

}
