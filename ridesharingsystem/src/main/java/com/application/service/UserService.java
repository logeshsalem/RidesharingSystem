package com.application.service;

import java.util.List;

import com.application.entity.Users;

public interface UserService {
	
	List<Users> findAll();
	
	Users findById(int id);
	
	Users saveUsers(Users users);
	
	void deleteById(int id);
	
	Users findByEmail(String user);

}
