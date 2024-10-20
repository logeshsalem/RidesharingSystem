package com.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.application.entity.Users;
import com.application.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{	
	
	private final UserRepository userRepository;
	
	
	
	@Override
	public Users saveUsers(Users users) {
		return userRepository.save(users);		
	}
	

	@Override
	public List<Users> findAll() {
		return userRepository.findAll();
		
	}

	@Override
	public Users findById(int id) {
		Optional<Users> users = userRepository.findById(id);
		if(users.isPresent()) {
			return users.get();
		}else {
			throw new RuntimeException("User Id not found");
		}
	}
	

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
		
	}

		
	
	@Override
	public Users findByEmail(String user) {
		Optional<Users> users = userRepository.findByEmail(user);
		if(users.isPresent()) {
			return users.get();
		}else {
			throw new RuntimeException("Email not found");
		}
	}

}
