package com.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.entity.Driver;
import com.application.entity.Users;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{

	Optional<Driver> findByEmail(String email);

}
