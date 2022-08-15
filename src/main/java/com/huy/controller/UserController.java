package com.huy.controller;

import java.util.ArrayList;
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

import com.huy.model.Role;
import com.huy.model.User;
import com.huy.service.RoleService;
import com.huy.service.UserService;


@RestController
//@RequestMapping("/user")
public class UserController implements CommandLineRunner{
	@Autowired
	private UserService service;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	  @Autowired private BCryptPasswordEncoder passwordEnc;
	 
	
	@GetMapping("/")
	public String home() {
		/*
		 * Role new_role = new Role("admin"); roleService.addRole(new_role);
		 */

		return ("<h1>Home</h1>");
	}
	
	@GetMapping("/users")
	public List<User> listAllUser(){
		return service.getAllUser();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User>getEmpId(@PathVariable int id){
		return service.getUserById(id);
	}
	
//	customer join
	  @PostMapping("/join") 
	  public String addUser(@RequestBody User user) {
	  
	  	String password = passwordEnc.encode(user.getPassword());
	  	user.setPassword(password);

		/*
		 * List<Role> roles = new ArrayList<>();
		 * roles.add(roleService.getRoleById(1).get()); user.setRoles(roles);
		 * user.getRoles().add(roleService.getRoleById(2).get()); System.out.print(
		 * user.getRoles()+"\n\n");
		 */
		  service.addUser(user);
		  run();
		  
		  return "<h1>Post join</h1>"; 
	  }
	  
	  @Override
	  public void run(String... args) {
		  sendEmail();
	  }
	  
	  void sendEmail() {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo("sample@gmail.com");
			email.setSubject("Add User");
			email.setText("Add user completed");
			javaMailSender.send(email);
		}
	 
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		service.deleteUser(id);
	}
	
	@PostMapping("/users/{id}")
	public void updateUserById(@PathVariable int id, @RequestBody User user){
		user.setUserId(id);
		service.updateUserById(user);
	}
}
