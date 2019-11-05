package com.rentmate.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

public class PostingDetails {
	private int postingId;
	private int apartmentId;
	private String address;
	private float latitude;
	private float longitude;
	
	private int managerId;
	private String managerName;
	private String managerEmail;
	private String managerPhone;
	
	private int tenantId;
	private int bedCount;
	private int bathCount;
	private float rent;
	private int gas;
	private int water;
	private int electric;
	private int internet;
	private int avgRating;
	
	private Date startAvailability;
	private Date endAvailability;
	private Image image;
	
	private List<Review> reviews;
	private List<Tour> tours;
	private List<Image> images;
	
	public PostingDetails() {}
	
	public PostingDetails(int postingId,int apartmentId,String address,float latitude,float longitude,
			              int managerId, String managerName, String managerEmail, String managerPhone, 
			              int tenantId,int bedCount,int bathCount,float rent, Date startAvailability, Date endAvailability, 
			              int gas,int water, int electric, int internet, int avgRating, 
			              Image image, List<Review> reviews, List<Tour> tours, List<Image> images) {
		
		this.postingId = postingId;
		this.apartmentId = apartmentId;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		
		this.managerId = managerId;
		this.managerEmail = managerEmail;
		this.managerName = managerName;
		this.managerPhone = managerPhone;
		
		this.tenantId = tenantId;
		this.bedCount = bedCount;
		this.bathCount = bathCount;
		this.rent = rent;
		this.gas = gas;
		this.water = water;
		this.electric = electric;
		this.internet = internet;
		this.avgRating = avgRating;
		
		this.image = image;
		
		this.startAvailability = startAvailability;
		this.endAvailability = endAvailability;
		
		this.reviews = reviews;
		this.tours = tours;
		this.images = images;
	}

	public int getPostingId() {
		return postingId;
	}

	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}

	public int getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(int apartmentId) {
		this.apartmentId = apartmentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getBedCount() {
		return bedCount;
	}

	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}

	public int getBathCount() {
		return bathCount;
	}

	public void setBathCount(int bathCount) {
		this.bathCount = bathCount;
	}

	public float getRent() {
		return rent;
	}

	public void setRent(float rent) {
		this.rent = rent;
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public int getGas() {
		return gas;
	}

	public void setGas(int gas) {
		this.gas = gas;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getElectric() {
		return electric;
	}

	public void setElectric(int electric) {
		this.electric = electric;
	}

	public int getInternet() {
		return internet;
	}

	public void setInternet(int internet) {
		this.internet = internet;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}
	
	
	
	
	
	
	
	
	
}
