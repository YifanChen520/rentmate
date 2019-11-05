package com.rentmate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.Tour;

@Repository
public interface TourRepository extends CrudRepository<Tour, Integer> {
	List<Tour> findAllByAid(int aid);
}
