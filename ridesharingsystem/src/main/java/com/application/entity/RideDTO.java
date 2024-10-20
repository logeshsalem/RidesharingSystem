package com.application.entity;

import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RideDTO {	
	
    private int id;
    private String origin;
    private String destination;
    private String rideDate;
    private String rideTime;
    private int availableSeats;
    private int totalSeats;
    private double pricePerSeat;
    private String rideStatus;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private int userId;  // Only store userId instead of entire Users object
    
    @PrePersist
    protected void onCreateAt() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public RideDTO(Ride ride) {        
		this.id = ride.getId();
        this.origin = ride.getOrigin();
        this.destination = ride.getDestination();
        this.rideDate = ride.getRideDate();
        this.rideTime = ride.getRideTime();
        this.availableSeats = ride.getAvailableSeats();
        this.totalSeats = ride.getTotalSeats();
        this.pricePerSeat = ride.getPricePerSeat();
        this.rideStatus = ride.getRideStatus();
        this.comment = ride.getComment();
        this.createdAt = ride.getCreatedAt();
        this.updateAt = ride.getUpdateAt();
        this.userId = ride.getUser().getId();  
    }

   

	

	

}

