package com.huy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huy.model.User;
import com.huy.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	public Optional<User> getUserById(int id){
		return userRepo.findById(id);		
	}
	
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}
	
	public void addUser(User user) {
		userRepo.save(user);
	}
	
	public void updateUserById(User user ) {	
		userRepo.save(user);
	}
}
