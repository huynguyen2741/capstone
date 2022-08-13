package com.huy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.huy.model.User;
import com.huy.service.UserService;


@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> listAllUser(){
		return service.getAllUser();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User>getEmpId(@PathVariable int id){
		return service.getUserById(id);
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		service.addUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		service.deleteUser(id);
	}
	
	@PostMapping("/users/{id}")
	public void updateUserById(@PathVariable int id, @RequestBody User emp){
		emp.setUserId(id);
		service.updateUserById(emp);
	}
}
