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

import com.application.entity.Driver;
import com.application.entity.Ride;
import com.application.entity.RideDTO;
import com.application.entity.Users;
import com.application.repository.DriverRepository;
import com.application.repository.RideRepository;
import com.application.repository.UserRepository;
import com.application.service.RideService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RideController {
	
	private final RideService rideService;
	private final RideRepository rideRepository;
	private final UserRepository userRepository;
	private final DriverRepository driverRepository;
	
	
	
	
	@PostMapping("/ride/{driverId}")
	public ResponseEntity<String> saveRide(@RequestBody Ride ride, @PathVariable int driverId){		
		
		 Optional<Driver> driverOpt = driverRepository.findById(driverId);
		    if (!driverOpt.isPresent()) {
		        return ResponseEntity.notFound().build();
		    }

		    Driver driver = driverOpt.get();
		    ride.setDriver(driver);                // set managed Driver entity
		    rideService.saveRide(ride);  
		return ResponseEntity.ok("ride saved Successfully");
	}
	
	
	
	@GetMapping("/ride/{rideId}")
	public ResponseEntity<RideDTO> getRideById(@PathVariable int rideId){
		Ride ride = rideService.findById(rideId);
		if (ride == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
		
		System.out.println(ride);
		
		RideDTO rideDTO = new RideDTO(ride);
		return ResponseEntity.ok(rideDTO);
	}
	
	@GetMapping("/ride")
	public ResponseEntity<List<RideDTO>> getAllRides(){
		List<RideDTO> rides = rideService.findAllRide();
	    return ResponseEntity.ok(rides);
	}
	
	@PutMapping("/ride/{rideId}/driver/{driverId}")
	public ResponseEntity<String> updateRideById(@PathVariable int rideId, @PathVariable int driverId, @RequestBody Ride ride){
		Optional<Ride> existingRide = rideRepository.findById(rideId);
		
		if(!existingRide.isPresent()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Ride updatedRide = existingRide.get();
		
		Optional<Driver> newUser = driverRepository.findById(driverId);
		if(!newUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		updatedRide.setOrigin(ride.getOrigin());
		updatedRide.setDestination(ride.getDestination());
		updatedRide.setRideDate(ride.getRideDate());
		updatedRide.setRideTime(ride.getRideTime());
		updatedRide.setAvailableSeats(ride.getAvailableSeats());
		updatedRide.setTotalSeats(ride.getTotalSeats());
		updatedRide.setPricePerSeat(ride.getPricePerSeat());
		updatedRide.setRideStatus(ride.getRideStatus());
		updatedRide.setComment(ride.getComment());
		
		updatedRide.setUpdateAt(LocalDateTime.now());		
		rideService.saveRide(updatedRide);
		return ResponseEntity.ok("ride updated successfully");		
	}
	
	@DeleteMapping("/ride/{rideId}")
	public ResponseEntity<String> deleteById(@PathVariable int rideId) {
		Ride ride = rideService.findById(rideId);
		if(ride==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Id "+rideId+" not found");
		}
		rideService.deleteById(rideId);
		return ResponseEntity.ok("ride id "+rideId+" deleted successfully");
	}
	
	
	@GetMapping("/ride/origin/{origin}")
	public ResponseEntity<List<Ride>> findByOrigin(@PathVariable String origin){
		List<Ride> ride = rideService.findByOrigin(origin);
		if(ride!=null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		System.out.println(ride);
		return ResponseEntity.ok(ride);
	}
	
	@GetMapping("/ride/destination/{destination}")
	public ResponseEntity<List<Ride>> findByDestinations(@PathVariable String destination){
		List<Ride> rides = rideService.findByDestination(destination);
		if(rides==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		System.out.println(rides);
		return ResponseEntity.ok(rides);
	}
	
}

