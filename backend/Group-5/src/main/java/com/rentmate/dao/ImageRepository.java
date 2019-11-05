package com.rentmate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.Image;

@Repository
public interface ImageRepository  extends CrudRepository<Image, Integer>{
	List<Image> findAllByAid(int aid);
	Image findFirstByAid(int aid);
}
