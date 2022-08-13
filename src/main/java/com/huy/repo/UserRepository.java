package com.huy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huy.model.User;

public interface UserRepository extends JpaRepository<User,Integer>  {

}
