package com.rentmate.model;

public class SpecificPostingRequest {
	private Integer postingId;
	
	public SpecificPostingRequest() {}
	
	public SpecificPostingRequest(Integer pId) {
		this.postingId = pId;
	}

	public Integer getPostingId() {
		return postingId;
	}

	public void setPostingId(Integer postingId) {
		this.postingId = postingId;
	}
	
	
}
 