package com.rentmate.model;

import java.util.List;

import com.rentmate.entity.PostingDetails;

public class SpecificPostingResponse {
	 private boolean success;
	 private PostingDetails postingDetails;
	 
	 public SpecificPostingResponse() {}
	 
	 public SpecificPostingResponse(boolean success, PostingDetails pd) {
		 this.success = success;
		 this.postingDetails = pd;
	 }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public PostingDetails getPostingDetails() {
		return postingDetails;
	}

	public void setPostingDetails(PostingDetails postingDetails) {
		this.postingDetails = postingDetails;
	}
	 
	 
}
