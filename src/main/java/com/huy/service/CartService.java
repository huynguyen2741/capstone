package com.huy.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.huy.model.Address;
import com.huy.model.Cart;
import com.huy.model.CartItem;
import com.huy.model.Order;
import com.huy.model.OrderProduct;
import com.huy.model.Product;
import com.huy.model.User;
import com.huy.repo.OrderProductRepository;
import com.huy.repo.OrderRepository;
import com.huy.repo.ProductRepository;
import com.huy.repo.UserRepository;

@Service
public class CartService implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderProductRepository orderProductRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static Cart c = new Cart();
	
	
//	add one item to the cart
	public void addItem(int id) throws Exception {
		Product p = productRepo.findById(id).get();
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
			productRepo.save(p);
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
		Product p = productRepo.findById(id).get();
//		If cart does not have item then return
		if(!validate(c,p)) {
			return "<h1>none</h1>";			
		}		
//		Iterate to find and remove item
		List<CartItem>list = c.getItems();
		for(CartItem l:list) {
			if (l.getId() == p.getProductId()) {
//				restore the inventory
				p.setQuantity(l.getQuantity() + p.getQuantity());
				list.remove(l);
				productRepo.save(p);
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
		Product p = productRepo.findById(id).get();
		if(!validate(c,p) || quantity > p.getQuantity()) {
			return;			
		}	
//		increase quantity and decrease inventory
		CartItem i = c.getItems().stream().filter((item)-> item.getId() == p.getProductId()).findFirst().get();
		i.setQuantity(i.getQuantity() + quantity);
//		decrease the inventory
		p.setQuantity(p.getQuantity() - quantity);
		productRepo.save(p);
		
	}

	public String checkOut(Principal principal) {
//		find the current user's id to connect to the order entity.
		User u = userRepo.findByUsername(principal.getName()).get();
		
//		get the current date
		DateTimeFormatter converter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String dateNow = "";
		dateNow = converter.format(now);
		
		String address = "";
		for (Address a : u.getAddresses()) {
			address += a.getStreet() +" "  + a.getAptNumber() + " " + a.getCity() + " " + a.getState() + " " + a.getCountry() + " " + a.getZipcode() + " ";
		}
		
		
//		create an Order to add to database
		Order o = new Order();
		o.setAmount(c.getCartTotal());
		o.setStatus("Ordered");
		o.setUser(u);
		o.setDate(dateNow);
		o.setShippingAdd(address);
		o.setBillingAdd(address);
		
//		save and get the order id
		o = orderRepo.save(o);
		
		List<CartItem> itemList = c.getItems();
		for(CartItem item: itemList) {
			OrderProduct op = new OrderProduct();
			op.setOrder(o);
			op.setProduct(productRepo.findById(item.getId()).get());
			op.setOrder_quantity(item.getQuantity());
			orderProductRepo.save(op);
		}
		
		c = new Cart();
		run();
		return "<h1>Check out complete</h1>";
	}
	
	@Override
	  public void run(String... args) {
//		  sendEmail();
	  }
	  
	  void sendEmail() {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo("sample@gmail.com");
			email.setSubject("Add User");
			email.setText("Check out completed");
			javaMailSender.send(email);
		}

}
