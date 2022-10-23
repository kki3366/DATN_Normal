package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DATN.Entity.users;

public interface UserRepository extends JpaRepository<users, String> {

	@Query("SELECT o FROM users o WHERE o.id=?1")
	users findId(String username);
	users findByEmail(String email);
	
}
