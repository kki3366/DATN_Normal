package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DATN.Entity.Cart;



public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query("SELECT o FROM Cart o WHERE o.product.id=?1")
    Cart  findByMaSP(Integer id);
}
