package com.rentmate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
	List<Review> findAllByAid(int aid);
}
