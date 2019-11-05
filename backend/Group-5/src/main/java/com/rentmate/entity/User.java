package com.rentmate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "uid")private Integer uid;
	@Column(name = "role")  private char role;
	@Column(name = "first_name")  private String firstname;
	@Column(name = "last_name")  private String lastname;
	@Column(name = "email") private String email;
	@Column(name = "username") private String username;
	@Column(name = "phone") private String phone;
	@Column(name = "password") private String password;
	@Column(name = "security_q_1") private String securityQ1;
	@Column(name = "security_q_1_a") private String securityQ1Ans;
	@Column(name = "security_q_2") private String securityQ2;
	@Column(name = "security_q_2_a") private String securityQ2Ans;
	@Column(name = "security_q_3") private String securityQ3;
	@Column(name = "security_q_3_a") private String securityQ3Ans;
	
	
	public User() {}
	
	public User(char role, String firstname, String lastname,
			    String email,String password, String username, String phone,
			    String securityQ1, String securityQ1Ans, String securityQ2, String securityQ2Ans,
			    String securityQ3, String securityQ3Ans) {
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.securityQ1 = securityQ1;
		this.securityQ1Ans = securityQ1Ans;
		this.securityQ2 = securityQ2;
		this.securityQ2Ans = securityQ2Ans;
		this.securityQ3 = securityQ3;
		this.securityQ3Ans = securityQ3Ans;
	}
	

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getSecurityQ1() {
		return securityQ1;
	}

	public void setSecurityQ1(String securityQ1) {
		this.securityQ1 = securityQ1;
	}

	public String getSecurityQ1Ans() {
		return securityQ1Ans;
	}

	public void setSecurityQ1Ans(String securityQ1Ans) {
		this.securityQ1Ans = securityQ1Ans;
	}

	public String getSecurityQ2() {
		return securityQ2;
	}

	public void setSecurityQ2(String securityQ2) {
		this.securityQ2 = securityQ2;
	}

	public String getSecurityQ2Ans() {
		return securityQ2Ans;
	}

	public void setSecurityQ2Ans(String securityQ2Ans) {
		this.securityQ2Ans = securityQ2Ans;
	}

	public String getSecurityQ3() {
		return securityQ3;
	}

	public void setSecurityQ3(String securityQ3) {
		this.securityQ3 = securityQ3;
	}

	public String getSecurityQ3Ans() {
		return securityQ3Ans;
	}

	public void setSecurityQ3Ans(String securityQ3Ans) {
		this.securityQ3Ans = securityQ3Ans;
	}

	
	

}
