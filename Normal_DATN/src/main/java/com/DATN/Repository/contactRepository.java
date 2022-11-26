package com.DATN.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DATN.Entity.Contact;


public interface contactRepository extends JpaRepository<Contact,Integer> {

	@Query("SELECT o FROM Contact o WHERE o.name LIKE ?1")
	Page<Contact> findByKeywords(String keywords, Pageable pgeable);
}
