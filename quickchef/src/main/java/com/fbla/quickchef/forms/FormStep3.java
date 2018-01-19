package com.fbla.quickchef.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class FormStep3 {
	@NotBlank(message="Username is a required field")
	private String nameOnCard;
	@NotBlank(message="Username is a required field")
	@Length(min = 19, max = 19)
	private String ccNum;
	@NotBlank(message="Username is a required field")
	private String ccExp;
	@NotBlank(message="Username is a required field")
	private String ccCvc;
	
	private String billAddress1;
	private String billAddress2;
	private String billCity;
	private String billState;
	private String billZip;
	
	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCcNum() {
		return ccNum;
	}
	
	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
	}
	
	public String getCcExp() {
		return ccExp;
	}
	
	public void setCcExp(String ccExp) {
		this.ccExp = ccExp;
	}
	
	public String getCcCvc() {
		return ccCvc;
	}
	
	public void setCcCvc(String ccCvc) {
		this.ccCvc = ccCvc;
	}

	public String getBillAddress1() {
		return billAddress1;
	}

	public void setBillAddress1(String billAddress1) {
		this.billAddress1 = billAddress1;
	}

	public String getBillAddress2() {
		return billAddress2;
	}

	public void setBillAddress2(String billAddress2) {
		this.billAddress2 = billAddress2;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillZip() {
		return billZip;
	}

	public void setBillZip(String billZip) {
		this.billZip = billZip;
	}
}
