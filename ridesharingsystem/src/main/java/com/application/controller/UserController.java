package com.application.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.application.entity.Ride;
import com.application.entity.Users;
import com.application.repository.UserRepository;
import com.application.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	private final UserRepository userRepository;
	
	
	@PostMapping("/user/driver")
	public ResponseEntity<String> saveDrivers(@RequestBody Users users){		
		Optional<Users> existingUser = userRepository.findByEmail(users.getEmail());		
		if(existingUser.isPresent()) {
			return ResponseEntity.badRequest().body("email already exist");
		}else {
			users.setRole("driver");
			 for (Ride ride : users.getRides()) {
		            ride.setUser(users);
		        }				
			userService.saveUsers(users);
			return ResponseEntity.ok("User "+users.getUsername()+" added Successfully");
		}			
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUsers(@RequestBody Users users){		
		Optional<Users> existingUser = userRepository.findByEmail(users.getEmail());		
		if(existingUser.isPresent()) {
			return ResponseEntity.badRequest().body("email already exist");
		}else {
			users.setRole("passenger");
			userService.saveUsers(users);
			return ResponseEntity.ok("User "+users.getUsername()+" added Successfully");
		}			
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<List<Users>> findAllUsers(){
		List<Users> users = userService.findAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<Users> findById(@PathVariable int userId){
		Users users = userService.findById(userId);
		return ResponseEntity.ok(users);
	}	
	
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody Users users){		
		users.setId(userId);
		users.setRegistrationDate(LocalDateTime.now());
		users.setEmail(users.getEmail());	
		users.setRole("passenger");
		Users theUsers = userService.saveUsers(users);
		return ResponseEntity.ok("User updated successfully");
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteById(@PathVariable int userId){
		Users users = userService.findById(userId);		
		if(users==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Id "+userId+" not found");
		}
		userService.deleteById(userId);
		return ResponseEntity.ok("User Id "+userId+" deleted successfully");
	}
	
	
	

}
