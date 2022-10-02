package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
