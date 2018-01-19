package com.fbla.quickchef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.quickchef.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	public Role findByRole(String role);
}
