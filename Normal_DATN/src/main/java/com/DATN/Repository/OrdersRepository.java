package com.DATN.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.DATN.Entity.Orders;


public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	@Query("SELECT o FROM Orders o WHERE o.user.id=?1")
    List<Orders>  findByIdUser(String user, Sort sort);
	
	@Query("SELECT o FROM Orders o WHERE o.user.id LIKE ?1")
	Page<Orders> findByKeywords(String keywords, Pageable pgeable);
	
	@Query("SELECT o FROM Orders o WHERE o.status LIKE ?1")
	Page<Orders> findByStatus(String status, Pageable pgeable);
}
