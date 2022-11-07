package com.DATN.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DATN.Entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT o FROM OrderDetail o WHERE o.order.id=?1")
    List<OrderDetail>  findByIdOrder(Integer user);
}
