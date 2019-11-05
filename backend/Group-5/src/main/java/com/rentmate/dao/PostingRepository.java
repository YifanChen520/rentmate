package com.rentmate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.Posting;


@Repository
public interface PostingRepository extends CrudRepository<Posting, Integer>{
	Posting findPostingByPostingId(int postingId);
}
 