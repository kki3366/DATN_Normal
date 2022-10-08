package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {

	Users findByEmail(String email);
}
