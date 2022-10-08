package com.DATN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	int Id;
	
	@Column(name = "Name")
	String nameProductCart;
	
	@Column(name = "Price")
	float priceProductCart;
	
	@Column(name = "Quanlity")
	int quanlityProductCart;
	
	@Column(name = "Img")
	int imgProductCart;
	
	@ManyToOne
	@JoinColumn(name = "Userid")
	Users user;
	
	@ManyToOne
	@JoinColumn(name = "idProduct")
	Product product;
	
	@OneToMany
	List<Product> products;
}
