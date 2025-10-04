package com.application.service;

import org.springframework.stereotype.Service;

import com.application.entity.Driver;
import com.application.repository.DriverRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
	
	private final DriverRepository driverRepository;

	@Override
	public Driver saveDriverDetails(Driver driver) {
		return driverRepository.save(driver);
		
	}
	
	

}
