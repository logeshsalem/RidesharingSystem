package com.application.entity;

import java.time.LocalDateTime;
import java.util.Set;

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
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

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
	
	@Column(name="role")
	private String role;
	
	@Column(name="registration_date")
	private LocalDateTime registrationDate;
	
	@PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDateTime.now();
    }
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonManagedReference
	private Set<Ride> rides;
	
	
	


	
	
	
	
}
