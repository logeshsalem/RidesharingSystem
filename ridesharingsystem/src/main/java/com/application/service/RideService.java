package com.application.service;

import java.util.List;

import com.application.entity.Ride;
import com.application.entity.RideDTO;

public interface RideService {
	
	Ride saveRide(Ride ride);
	
	List<RideDTO> findAllRide();
	
	Ride findById(int id);
	
	void deleteById(int id);
	
	List<Ride> findByOrigin(String ride);
	
	List<Ride> findByDestination(String ride);
	
	

}
