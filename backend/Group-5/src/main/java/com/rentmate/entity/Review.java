package com.rentmate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity 
@EntityListeners(AuditingEntityListener.class)
@Table(name="review")
public class Review { 
	@Id
	@Column(name = "rvid") private Integer rvid;
	@Column(name = "uid") private Integer uid;
	@Column(name = "first_name") private String firstName;
	@Column(name = "last_name") private String lastName;
	@Column(name = "rating") private Integer rating;
	@Column(name = "review") private String review;
	@Column(name = "aid") private Integer aid;
	
	public Review() {
		super();
	}
	
	public Review(Integer rvid, Integer uid, String firstName, String lastName, 
				  Integer rating, String review) {
		this.rvid = rvid;
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
		this.review = review;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getRvid() {
		return rvid;
	}

	public void setRvid(Integer rvid) {
		this.rvid = rvid;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
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
	
	
	
}
