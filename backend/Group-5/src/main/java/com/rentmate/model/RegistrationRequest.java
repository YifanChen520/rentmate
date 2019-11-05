package com.rentmate.model;


public class RegistrationRequest {
	private char role;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String password;
	private String securityquestion1;
	private String answer1;
	private String securityquestion2;
	private String answer2;
	private String securityquestion3;
	private String answer3;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getRole() {
		return role;
	}
	public void setRole(char role) {
		this.role = role;
	}
	public String getSecurityquestion1() {
		return securityquestion1;
	}
	public void setSecurityquestion1(String securityquestion1) {
		this.securityquestion1 = securityquestion1;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getSecurityquestion2() {
		return securityquestion2;
	}
	public void setSecurityquestion2(String securityquestion2) {
		this.securityquestion2 = securityquestion2;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getSecurityquestion3() {
		return securityquestion3;
	}
	public void setSecurityquestion3(String securityquestion3) {
		this.securityquestion3 = securityquestion3;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	
}
