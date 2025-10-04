package com.application.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private long phoneNumber;
	
	@Column(name="password")
	private String password;
	
	@Column(name="license_number")
	private String licenseNumber;
	
	@Column(name="vehicle_details")
	private String vehicleDetails;
	
	@Column(name="registration_date")
	private LocalDateTime registrationDate;
	
	@PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDateTime.now();
    }
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "driver", cascade = {CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonManagedReference
	private List<Ride> rides = new ArrayList<>();
	
	
	


	
	
	
	
}
