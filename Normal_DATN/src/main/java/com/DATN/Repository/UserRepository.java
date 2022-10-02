package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
