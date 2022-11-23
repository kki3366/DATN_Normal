package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.Contact;

public interface contactRepository extends JpaRepository<Contact,Integer> {

}
