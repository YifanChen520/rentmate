package com.rentmate.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Integer>{
	User findUserByEmailAndPassword(String email,String Password);
	User findUserByEmail(String email);
	User findByUid(int uid);

	@Transactional
	@Modifying
	@Query("update User set password = ?1 where email = ?2")
	int UpdatePassword(String password, String email);
}
