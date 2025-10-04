package com.application.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Ride")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ride {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="origin")
	private String origin;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="ride_date")
	private String rideDate;
	
	@Column(name="ride_time")
	private String rideTime;
	
	@Column(name="available_seats")
	private int availableSeats;
	
	@Column(name="total_seats")
	private int totalSeats;
	
	@Column(name="price_perseat")
	private double pricePerSeat;
	
	@Column(name="ride_status")
	private String rideStatus;
	
	@Column(name="comments")
	private String comment;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updateAt;
	
	@PrePersist
    protected void onCreateAt() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="driver_id", nullable = false)
//	@JsonIgnore
	@JsonBackReference
	private Driver driver;

	@Override
	public String toString() {
		return "Ride [id=" + id + ", origin=" + origin + ", destination=" + destination + ", rideDate=" + rideDate
				+ ", rideTime=" + rideTime + ", availableSeats=" + availableSeats + ", totalSeats=" + totalSeats
				+ ", pricePerSeat=" + pricePerSeat + ", rideStatus=" + rideStatus + ", comment=" + comment
				+ ", createdAt=" + createdAt + ", updateAt=" + updateAt;
	}

	
	
	
	
	
	


	
	

}
