package com.fbla.quickchef.forms;

public class PreferenceForm {
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
}
