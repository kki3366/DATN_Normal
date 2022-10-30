package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
