package com.huy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huy.model.User;

public interface UserRepository extends JpaRepository<User,Integer>  {
	Optional<User> findByUserName(String username);
}
