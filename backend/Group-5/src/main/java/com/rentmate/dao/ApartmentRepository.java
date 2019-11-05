package com.rentmate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.Apartment;

@Repository
public interface ApartmentRepository   extends CrudRepository<Apartment, Integer>{
	Apartment findApartmentByPostingId(int postingId);
}
 