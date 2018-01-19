package com.fbla.quickchef.forms;

import org.hibernate.validator.constraints.NotBlank;

public class FormStep1 {
	private String username;	
	private String password;
	@NotBlank(message="Email is a required field")
	private String email;
	@NotBlank(message="First name is a required field")
	private String firstName;
	@NotBlank(message="Last name is a required field")
	private String lastName;
	@NotBlank(message="Address 1 is a required field")
	private String address1;
	private String address2;
	@NotBlank(message="City is a required field")
	private String city;
	@NotBlank(message="State is a required field")
	private String state;
	@NotBlank(message="Zip is a required field")
	private String zip;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
