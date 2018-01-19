package com.fbla.quickchef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.quickchef.model.UserPreference;

public interface UserPreferenceRepo extends JpaRepository<UserPreference, String> {
	
}
