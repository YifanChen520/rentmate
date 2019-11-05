package com.rentmate.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity 
@EntityListeners(AuditingEntityListener.class)
@Table(name="apartment")
public class Apartment { 
	@Id
	@Column(name = "aid")  private Integer apartmentId;
	@Column(name = "address")  private String address;
	@Column(name = "latitude")  private Float latitude;
	@Column(name = "longitude")  private Float longitude;
	@Column(name = "manager_id") private Integer managerId;
	@Column(name = "tenant_id") private Integer tenantId;
	@Column(name = "bed_count") private Integer bedCount;
	@Column(name = "bath_count") private Integer bathCount;
	@Column(name = "pid") private Integer postingId;
	@Column(name = "rent") private Float rent;
	@Column(name = "gas") private Integer gas;
	@Column(name = "water") private Integer water;
	@Column(name = "electric") private Integer electric;
	@Column(name = "internet") private Integer internet;
	
	
	public Apartment() {}
	
	public Apartment(int aid, String address, float latitude, float longitude, int managerId, int tenantId, int bedCount, int bathCount, int postingId, 
			         Float rent, Integer gas, Integer water, Integer electric, Integer internet) {
		this.apartmentId = aid;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.managerId = managerId;
		this.tenantId = tenantId;
		this.bedCount = bedCount;
		this.bathCount = bathCount;
		this.postingId = postingId;
		this.rent = rent;
		this.gas = gas;
		this.water = water;
		this.internet = internet;
		this.electric = electric;
	}

	public Integer getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getBedCount() {
		return bedCount;
	}

	public void setBedCount(Integer bedCount) {
		this.bedCount = bedCount;
	}

	public Integer getBathCount() {
		return bathCount;
	}

	public void setBathCount(Integer bathCount) {
		this.bathCount = bathCount;
	}

	public Integer getPostingId() {
		return postingId;
	}

	public void setPostingId(Integer postingId) {
		this.postingId = postingId;
	}

	public Float getRent() {
		return rent;
	}

	public void setRent(Float rent) {
		this.rent = rent;
	}

	public Integer getGas() {
		return gas;
	}

	public void setGas(Integer gas) {
		this.gas = gas;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getElectric() {
		return electric;
	}

	public void setElectric(Integer electric) {
		this.electric = electric;
	}

	public Integer getInternet() {
		return internet;
	}

	public void setInternet(Integer internet) {
		this.internet = internet;
	}	
	
	
	
}


