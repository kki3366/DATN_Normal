package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.DATN.Entity.users;
import com.DATN.Repository.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository user;
	@Override
	public List<users> findAllAccountService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<users> getAllService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public users saveAccountService(users accounts) {
		// TODO Auto-generated method stub
		return user.save(accounts);
	}

	@Override
	public Optional<users> findByUsernameService(String username) {
		// TODO Auto-generated method stub
		return user.findById(username);
	}

	@Override
	public void deleteAccountById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int checkAccountName(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<users> findByIdAccount(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		users acc = user.getById(username);
		String pass = acc.getPassword();
		Boolean role = acc.getAdmin();
		
		UserDetails user = User.withUsername(acc.getId()).password(pass).roles(role+"").build();
	return user;
	}

	public void loginfromOAuth2(OAuth2AuthenticationToken oauth2) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String name = oauth2.getPrincipal().getAttribute("name");
		String pass = Long.toHexString(System.currentTimeMillis());
		Boolean role = false;
		System.err.println(pass);
		
		UserDetails user = User.withUsername(name).password(pe.encode(pass)).roles(role+"").build();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String email = oauth2.getPrincipal().getAttribute("email");
		if(findByEmailService(email) == null) {
			users acc = new users();
			acc.setId(System.currentTimeMillis()+"");
			acc.setActivated(true);
			acc.setAdmin(role);
			acc.setFullname(name);
			acc.setPassword(pass);
			acc.setEmail(email);
			saveAccountService(acc);
		}
	}

	@Override
	public users findByEmailService(String email) {
		
		return user.findByEmail(email);
	}
}
