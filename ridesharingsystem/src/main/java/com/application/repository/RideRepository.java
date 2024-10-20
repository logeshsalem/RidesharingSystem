package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.entity.Ride;
import java.util.List;
import java.util.Optional;


@Repository
public interface RideRepository extends JpaRepository<Ride, Integer>{
	
	List<Ride> findByOrigin(String origin);
	
	List<Ride> findByDestination(String destination);
	

}
