package com.huy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huy.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>  {
	Optional<Role> findByRole(String role);
}
