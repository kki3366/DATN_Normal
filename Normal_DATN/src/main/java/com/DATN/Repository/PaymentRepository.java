package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DATN.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String>{

	@Query(value = "select * from Payment where order_id=:id", nativeQuery = true)
	Payment findByOrderId(@Param("id") int id);
	
}
