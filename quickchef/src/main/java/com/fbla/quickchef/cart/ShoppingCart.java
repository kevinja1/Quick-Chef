package com.fbla.quickchef.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class represent the cart on the website.
 *
 */
public class ShoppingCart {
	private Map<Long, CartItem> cartItems = new HashMap<>();

	public Map<Long, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<Long, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public int getCartItemCount() {
		int itemCount = 0;
		for(CartItem item : cartItems.values()) {
			itemCount += item.getQuantity();
		}
		
		return itemCount;
	}
	
	public List<CartItem> getAllItems() {
		return new ArrayList<CartItem>(cartItems.values());
	}
	
	public double getCartShippingFee() {
		if(getCartSubTotal() < 40d) {
			return 10d;
		}
		
		return 0d;
	}
	
	public double getCartSubTotal() {
		return getAllItems().stream()
			.mapToDouble(item -> item.getItemTotal())
			.sum();
	}
	
	public double getCartTotal() {
		return getCartSubTotal() + getCartShippingFee();
	}
}
