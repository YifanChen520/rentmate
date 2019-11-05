package com.rentmate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity 
@EntityListeners(AuditingEntityListener.class)
@Table(name="tour")
public class Tour {
	@Id
	@Column(name = "tid") private Integer tid;
	@Column(name = "uid") private Integer uid;
	@Column(name = "start_time") private Timestamp startTime;
	@Column(name = "end_time") private Timestamp endTime;
	@Column(name = "aid") private Integer aid;
	
	public Tour() {}
	
	public Tour(Integer tid, Integer uid, Timestamp startTime, Timestamp endTime, Integer aid) {
		this.tid = tid;
		this.uid = uid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.aid = aid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	
}
