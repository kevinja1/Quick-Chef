package com.fbla.quickchef.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.thymeleaf.util.StringUtils;

@Entity
@Table(name="tbl_users_payment_info")
public class UserPaymentInfo {
	@Id @Column(name="user_id")
	private String username;
	private String ccNum;
	private String ccExp;
	private String ccCvc;
	
	private boolean billingAddressInd = false;
	private String nameOnCard;
	private String billAddress1;
	private String billAddress2;
	private String billCity;
	private String billState;
	private String billZip;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isBillingAddressInd() {
		return billingAddressInd;
	}

	public void setBillingAddressInd(boolean billingAddressInd) {
		this.billingAddressInd = billingAddressInd;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
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
	
	public String getMaskCcNum() {
		return String.format("XXXX-XXXX-XXXX-%s", StringUtils.substring(ccNum, 12, ccNum.length()));
	}
}
