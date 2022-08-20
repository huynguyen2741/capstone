package com.huy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.huy.model.Address;
import com.huy.model.Role;
import com.huy.model.User;
import com.huy.repo.RoleRepository;
import com.huy.repo.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	
	
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
		
		user.setRoles(Arrays.asList(new Role("USER")));	
		
		
		Address user_a = user.getAddresses().get(0);
		Address a = new Address();
		a.setAptNumber(user_a.getAptNumber());
		a.setStreet(user_a.getStreet());
		a.setCity(user_a.getCity());
		a.setState(user_a.getState());
		a.setCountry(user_a.getCountry());
		a.setZipcode(user_a.getZipcode());
		a.setUser(user);
		  
		user.setAddresses(Arrays.asList(a));
		userRepo.save(user);
	}
	
	public void updateUserByUsername(String username,User user ) {	
		User u = userRepo.findByUsername(username).get();
		
		user.setRoles(u.getRoles());
		user.setUserId(u.getUserId());
		user.setUsername(u.getUsername());
//		if password is blank set to current password
//		else hash and save new password
		userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   return userRepo.findByUsername(username)
	           .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
	}
	
	
}
