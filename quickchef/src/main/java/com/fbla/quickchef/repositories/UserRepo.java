package com.fbla.quickchef.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.quickchef.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	User findOne(String userId);
	
	List<User> findByFirstName(String firstName);
}
