package com.rentmate.model;

import java.util.ArrayList;

import com.rentmate.entity.Apartment;
 
public class ApartmentsResponse {
	private boolean success;
	private ArrayList<Apartment> apartmentList;
	 
	public ApartmentsResponse(boolean success, ArrayList<Apartment> aptList) {
		this.success = success;
		this.apartmentList = aptList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<Apartment> getApartmentList() {
		return apartmentList;
	}

	public void setApartmentList(ArrayList<Apartment> apartmentList) {
		this.apartmentList = apartmentList;
	}
	
	
}
