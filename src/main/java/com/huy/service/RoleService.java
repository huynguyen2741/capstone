package com.huy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huy.model.Role;
import com.huy.model.User;
import com.huy.repo.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository repo;
	
	public void addRole(Role role) {
		repo.save(role);
	}
	public Optional<Role> getRoleById(int id){
		return repo.findById(id);		
	}
}
