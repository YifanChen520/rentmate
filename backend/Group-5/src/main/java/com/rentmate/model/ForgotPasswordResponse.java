package com.rentmate.model;

import java.util.List;

import com.rentmate.entity.User;

public class ForgotPasswordResponse {
	private boolean success;
	List<SecurityQuestionAnswer> securityQuestionList;
	

	 public ForgotPasswordResponse(boolean success, List<SecurityQuestionAnswer> securityQuestionList) {
		 this.success = success;
		 this.securityQuestionList = securityQuestionList;
	 }
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<SecurityQuestionAnswer> getSecurityQuestionList() {
		return securityQuestionList;
	}
	public void setSecurityQuestionList(List<SecurityQuestionAnswer> securityQuestionList) {
		this.securityQuestionList = securityQuestionList;
	}
	
}
