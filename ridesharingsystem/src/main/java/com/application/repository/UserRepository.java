package com.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	Optional<Users> findByEmail(String email);

}
