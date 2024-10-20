package com.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.application.entity.Ride;
import com.application.entity.RideDTO;
import com.application.repository.RideRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RideServiceImpl implements RideService{
	
	private final RideRepository rideRepository;
	

	@Override
	public Ride saveRide(Ride ride) {		
		return rideRepository.save(ride);
	}

	@Override
	public List<RideDTO> findAllRide() {
        return rideRepository.findAll().stream()
                .map(RideDTO::new)  // Assuming RideDTO has a constructor that takes a Ride
                .collect(Collectors.toList());
    }

	@Override
	public Ride findById(int id) {		
		Optional<Ride> rides = rideRepository.findById(id);
		Ride ride = null; 
		if(rides.isPresent()) {
			ride = rides.get();
		}else {
			throw new RuntimeException("ride id not found");
		}
		return ride;
	}

	@Override
	public void deleteById(int id) {
		Optional<Ride> rides = rideRepository.findById(id);
		if(rides.isEmpty()) {
			throw new RuntimeException("delete id not found");
		}else {
			rideRepository.deleteById(id);
		}
		
	}

	@Override
	public List<Ride> findByOrigin(String ride) {
		List<Ride> rides = rideRepository.findByOrigin(ride);
		if(rides.isEmpty()) {
			throw new RuntimeException("origin not found");
		}else {
			return rideRepository.findByOrigin(ride);
		}
	}

	@Override
	public List<Ride> findByDestination(String ride) {
		List<Ride> rides = rideRepository.findByDestination(ride);
		if(rides.isEmpty()) {
			throw new RuntimeException("destination not found");
		}else {
			return rideRepository.findByDestination(ride);
		}
	}
	
	

}
