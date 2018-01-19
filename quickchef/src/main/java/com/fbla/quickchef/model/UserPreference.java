package com.fbla.quickchef.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_users_pref")
public class UserPreference {
	@Id @Column(name="user_id")
	private String username;
	
	private int numMealsPerWeek;
	private int numServings;
 
	// Ingredient preference
	private boolean meatInd;
	private boolean seafoodInd;
	private boolean vagetableInd;
	
	// Dietary Option
	private boolean lowCarbInd;
	private boolean lowCalorieInd;
	private boolean glutenFreeInd;
	
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

	public boolean isMeatInd() {
		return meatInd;
	}

	public void setMeatInd(boolean meatInd) {
		this.meatInd = meatInd;
	}

	public boolean isSeafoodInd() {
		return seafoodInd;
	}

	public void setSeafoodInd(boolean seafoodInd) {
		this.seafoodInd = seafoodInd;
	}

	public boolean isVagetableInd() {
		return vagetableInd;
	}

	public void setVagetableInd(boolean vagetableInd) {
		this.vagetableInd = vagetableInd;
	}

	public boolean isLowCarbInd() {
		return lowCarbInd;
	}

	public void setLowCarbInd(boolean lowCarbInd) {
		this.lowCarbInd = lowCarbInd;
	}

	public boolean isLowCalorieInd() {
		return lowCalorieInd;
	}

	public void setLowCalorieInd(boolean lowCalorieInd) {
		this.lowCalorieInd = lowCalorieInd;
	}

	public boolean isGlutenFreeInd() {
		return glutenFreeInd;
	}

	public void setGlutenFreeInd(boolean glutenFreeInd) {
		this.glutenFreeInd = glutenFreeInd;
	}
	
	public String getPreferenceText() {
		StringBuilder strBuilder = new StringBuilder();
		if(meatInd) {
			strBuilder.append("meat");
		}
		
		if(meatInd && seafoodInd && vagetableInd) {
			strBuilder.append(", ");
		} else if(meatInd && (seafoodInd || vagetableInd)){
			strBuilder.append(" and ");
		}
			
		if(seafoodInd) {
			strBuilder.append("seafood");
		}
		
		if(meatInd && seafoodInd && vagetableInd) {
			strBuilder.append(" and ");
		}
		
		if(vagetableInd) {
			strBuilder.append(" vegetables");
		}
		
		return strBuilder.toString();
	}
	
	public String getDietPrefText() {
		StringBuilder strBuilder = new StringBuilder();
		if(lowCarbInd) {
			strBuilder.append("low-carb");
		}
		
		if(lowCarbInd && lowCalorieInd && glutenFreeInd) {
			strBuilder.append(", ");
		} else if(lowCarbInd && (lowCalorieInd || glutenFreeInd)){
			strBuilder.append(" and ");
		}
			
		if(lowCalorieInd) {
			strBuilder.append("low-calorie");
		}
		
		if(lowCarbInd && lowCalorieInd && glutenFreeInd) {
			strBuilder.append(" and ");
		}
		
		if(glutenFreeInd) {
			strBuilder.append("gluten-free");
		}
		
		return strBuilder.toString();
	}
}
