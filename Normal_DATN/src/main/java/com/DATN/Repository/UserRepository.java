package com.DATN.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DATN.Entity.Orders;
import com.DATN.Entity.users;

public interface UserRepository extends JpaRepository<users, String> {

	@Query("SELECT o FROM users o WHERE o.id=?1")
	users findId(String username);
	
	users findByEmail(String email);
	@Query("SELECT o FROM users o WHERE o.phone=?1")
	users findByPhone(String phone);
	@Query("SELECT o FROM users o WHERE o.id=?1 AND o.email=?2")
	users findByEmailAndUser(String user,String email);
	@Query("SELECT o FROM users o WHERE o.id=?1 AND o.phone=?2")
	users findByPhoneAndUser(String user,String phone);
	
	@Query("SELECT o FROM users o WHERE o.id LIKE ?1")
	Page<users> findByKeywords(String keywords, Pageable pgeable);
}
