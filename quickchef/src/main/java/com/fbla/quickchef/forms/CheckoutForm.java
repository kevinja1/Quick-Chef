package com.fbla.quickchef.forms;

import java.util.ArrayList;
import java.util.List;

import com.fbla.quickchef.cart.CartItem;

public class CheckoutForm {
	private List<CartItem> items = new ArrayList<>();

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public double getCartTotal() {
		return items.stream()
			.mapToDouble(item -> item.getItemTotal())
			.sum();
	}
}
