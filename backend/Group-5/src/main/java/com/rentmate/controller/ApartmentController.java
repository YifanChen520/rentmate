package com.rentmate.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmate.dao.ApartmentRepository;
import com.rentmate.entity.Apartment;
import com.rentmate.model.ApartmentsResponse;

@RestController
public class ApartmentController {
	@Autowired
	ApartmentRepository apartmentRepo;
	
	@CrossOrigin
	@GetMapping(path="/apartments")
	public ResponseEntity<?> getAllApartments() {
		Iterable<Apartment> iterable = apartmentRepo.findAll();
		
		if(iterable != null) {
			ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();
			for (Apartment apt : iterable) {
				apartmentList.add(apt);
		    }
			return ResponseEntity.ok(new ApartmentsResponse(true, apartmentList));
		} else {
			return ResponseEntity.ok(new ApartmentsResponse(false, null));
		}
		
	}
	
}
