package com.fbla.quickchef.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_order")
public class Order {
	private static double PRICE_PER_MEAL = 7.99; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	private String username;
			
	private int numMealsPerWeek;
	private int numServings;
	
	private String subscriptionStatus = "Active";
	private Date orderDate;
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getNumMealsPerWeek() {
		return numMealsPerWeek;
	}

	public void setNumMealsPerWeek(int numMealsPerWeek) {
		this.numMealsPerWeek = numMealsPerWeek;
	}

	public int getNumServings() {
		return numServings;
	}

	public void setNumServings(int numServings) {
		this.numServings = numServings;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public double getSubTotal() {
		return numMealsPerWeek * numServings * PRICE_PER_MEAL;
	}
	
	public double getShipping() {
		if(getSubTotal() < 40d) {
			return 10d;
		}
		
		return 0d;
	}
	
	public double getTotal() {
		return getSubTotal() + getShipping();
	}
}
