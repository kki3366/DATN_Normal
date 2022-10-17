package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.users;

public interface UserRepository extends JpaRepository<users, String> {

	users findByEmail(String email);
}
