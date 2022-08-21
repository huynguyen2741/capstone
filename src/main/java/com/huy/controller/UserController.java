package com.huy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huy.model.Address;
import com.huy.model.Role;
import com.huy.model.User;
import com.huy.repo.UserRepository;
import com.huy.service.RoleService;
import com.huy.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController implements CommandLineRunner{
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	customer join
	  @PostMapping("/join") 
	  public String addUser(@RequestBody User user) {
		  
		  String password = passwordEncoder.encode(user.getPassword());
		  user.setPassword(password);
		  user.setRoles(Arrays.asList(new Role("USER")));
		  service.addUser(user);
		  
		  run();
		  
		  return "<h1>Post join</h1>"; 
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
	 
//	Assume: all details are required for updating.
	@PostMapping("/update/{username}")
	public void updateUserByUsername(@PathVariable String username, @RequestBody User user){
		
		service.updateUserByUsername(username,user);
	}
}
