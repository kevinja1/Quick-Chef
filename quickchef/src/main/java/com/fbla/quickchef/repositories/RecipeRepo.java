package com.fbla.quickchef.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.quickchef.model.Recipe;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
	public List<Recipe> findByCategory(String category);
}
