package com.rentmate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentmate.entity.UserOTP;

@Repository
public interface UserOTPRepository   extends CrudRepository<UserOTP, Integer>{
	UserOTP findUserOTPByEmail(String email);
}
