package com.rentmate.model;

import java.util.List;

import com.rentmate.entity.PostingDetails;

public class PublicViewResponse {
	 private boolean success;
	 private List<PostingDetails> list;
	 
	 public PublicViewResponse() {}
	 
	 public PublicViewResponse(boolean success, List<PostingDetails> list) {
		 this.success = success;
		 this.list = list;
	 }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<PostingDetails> getList() {
		return list;
	}

	public void setList(List<PostingDetails> list) {
		this.list = list;
	}
	
}
