package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.DATN.Entity.Users;



public interface UserService extends UserDetailsService {

	List<Users> findAllAccountService();
	List<Users> getAllService();
	
	Users saveAccountService(Users accounts);
	
	Optional<Users> findByUsernameService(String username);
	Users findByEmailService(String email);

	void deleteAccountById(int id);

	int checkAccountName(String username);
	Optional<Users> findByIdAccount(int id);
	
}
