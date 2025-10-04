package com.application.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.entity.Driver;
import com.application.entity.Users;
import com.application.repository.DriverRepository;
import com.application.service.DriverService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DriverController {
	
	private final DriverRepository driverRepository;
	private final DriverService driverService;
	
	
//	@PostMapping("/driver")
//	public ResponseEntity<String> saveDriver(@RequestBody Driver driver) {
//		
//		driverService.saveDriverDetails(driver);
//		return ResponseEntity.ok("the driver "+driver.getUsername()+" saved successfully");
//		
//	}
	
	
	@PostMapping("/driver")
	public ResponseEntity<String> saveDrivers(@RequestBody Driver driver){		
		Optional<Driver> existingUser = driverRepository.findByEmail(driver.getEmail());		
		if(existingUser.isPresent()) {
			return ResponseEntity.badRequest().body("email already exist");
		}else {
			driverService.saveDriverDetails(driver);
			return ResponseEntity.ok("Driver "+driver.getUsername()+" added Successfully");
		}			
	}

}
