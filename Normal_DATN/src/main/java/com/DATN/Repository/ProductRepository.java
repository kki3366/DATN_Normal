package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
