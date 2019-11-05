package com.rentmate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
 
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="userotp")
public class UserOTP {
	@Id
	@Column(name = "email") private String email;
	@Column(name = "otp") private int otp;
	
	public UserOTP() {}
	
	public UserOTP(String emial, int otp) {
		this.email = emial;
		this.otp = otp;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getOtp() {
		return otp;
	}


	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	
}
