package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.DATN.Entity.users;



public interface UserService extends UserDetailsService {

	List<users> findAllAccountService();
	List<users> getAllService();
	
	users saveAccountService(users accounts);
	
	Optional<users> findByUsernameService(String username);
	
	users findByEmailService(String email);
	users findByPhoneService(String phone);
	void deleteAccountById(int id);

	int checkAccountName(String username);
	Optional<users> findByIdAccount(int id);
	
}
