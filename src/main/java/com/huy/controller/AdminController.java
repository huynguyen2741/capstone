package com.huy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huy.model.Address;
import com.huy.model.Product;
import com.huy.model.Role;
import com.huy.model.User;
import com.huy.service.ProductService;
import com.huy.service.RoleService;
import com.huy.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController implements CommandLineRunner{
	@Autowired
	private UserService service;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	 

	@PostMapping("/join") 
	  public String addAdmin(@RequestBody User user) {

//		  User new_user = new User();
//		  new_user.setFirstName(user.getFirstName());
//		  new_user.setLastName(user.getLastName());
//		  new_user.setEmail(user.getEmail());
//		  new_user.setUsername(user.getUsername());
//		  new_user.setPassword(user.getPassword());
//		  new_user.setContact(user.getContact());
//		  new_user.setSSN(user.getSSN());
//		  new_user.setRoles(Arrays.asList(new Role("ADMIN")));		  
//		  
//		  Address add = user.getAddresses().get(0);
//		  add.setUser(new_user);
//		  new_user.setAddresses(Arrays.asList(add));
//
//		  String password = passwordEnc.encode(user.getPassword());
//	  	  new_user.setPassword(password);
//	  	 
//	  
//		  service.addUser(new_user);
//		  run();
		  
		
		String password = passwordEncoder.encode(user.getPassword());
		  user.setPassword(password);
		  user.setRoles(Arrays.asList(new Role("ADMIN")));
		  service.addUser(user);
		  
		  return "<h1>Post ADMIN join</h1>"; 
	  }
	
	 @Override
	  public void run(String... args) {
//		  sendEmail();
	  }
	  
	  void sendEmail() {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo("sample@gmail.com");
			email.setSubject("Add User");
			email.setText("Add user completed");
			javaMailSender.send(email);
		}
	
	@GetMapping("/users")
	public List<User> listAllUser(){
		List<User> list = service.getAllUser();
		return list;
	}
	
	@GetMapping("/{id}")
	public Optional<User>getUserId(@PathVariable int id){
		return service.getUserById(id);
	}
	
	
}
