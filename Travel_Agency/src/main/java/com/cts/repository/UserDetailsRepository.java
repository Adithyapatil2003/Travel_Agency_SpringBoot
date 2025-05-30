package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.User;


public interface UserDetailsRepository extends JpaRepository<User,Integer >{
	
	Optional<User>  findByEmail(String name); 
	Optional<User>  findByUsername(String username);
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByEmailOrUsername(String email, String username);
	
}
