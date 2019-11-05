package com.rentmate.entity;

import java.sql.Date;

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
@Table(name="posting")
public class Posting {
	@Id 
	@Column(name = "pid")private Integer postingId;
	@Column(name = "aid")  private int apartmentId;
	@Column(name = "start_availability") private Date startAvailability;
	@Column(name = "end_availability") private Date endAvailability;
	
	public Posting() {}
	
	public Posting(int postingId, int apartmentId, Date startAvailability, Date endAvailability) {
		this.postingId = postingId;
		this.apartmentId = apartmentId;
		this.startAvailability = startAvailability;
		this.endAvailability = endAvailability;
	}

	public Integer getPostingId() {
		return postingId;
	}

	public void setPostingId(Integer postingId) {
		this.postingId = postingId;
	}

	public Date getStartAvailability() {
		return startAvailability;
	}

	public void setStartAvailability(Date startAvailability) {
		this.startAvailability = startAvailability;
	}

	public Date getEndAvailability() {
		return endAvailability;
	}

	public void setEndAvailability(Date endAvailability) {
		this.endAvailability = endAvailability;
	}

	public int getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(int apartmentId) {
		this.apartmentId = apartmentId;
	}
	
	
	
}
