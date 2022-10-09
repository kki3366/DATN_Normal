package com.DATN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
@Table(name = "Users")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable{
	
	@Id
	@Column(name = "Id")
	String id;
	
	@Column(name = "Password")
	String password;
	
	@Column(name = "fullname")
	String fullname;
	
	@Column(name = "Email")
	String email;
	//
	@Column(name = "Activated")
	Boolean activated;
	
	@Column(name = "Admin")
	Boolean admin;
	
//	@OneToMany
//	List<Cart> carts;
//
//	@OneToMany
//	List<Orders> orders;
}
