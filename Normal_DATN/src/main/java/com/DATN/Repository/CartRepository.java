package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
