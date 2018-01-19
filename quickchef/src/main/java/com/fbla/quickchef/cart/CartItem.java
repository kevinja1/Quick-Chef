package com.fbla.quickchef.cart;

/**
 * This class represent the cart item in the shopping cart.
 */
public class CartItem {
	private Long recipeId;
	private String name;
	private int quantity = 1;

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}
	
	public double getItemTotal() {
		return quantity * 9.99;
	}

	@Override
	public String toString() {
		return "CartItem [recipeId=" + recipeId + ", name=" + name + ", quantity=" + quantity + "]";
	}
}
