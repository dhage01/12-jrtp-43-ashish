package com.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.Country;
import com.ashokit.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
	//@Query("select email from User where email=email")
	public User isEmailPresent(String email);
	
	//@Query("select email,password,accStatus from User where email=email AND password=password ")
	public User checkLogin(String email,String password);

}
