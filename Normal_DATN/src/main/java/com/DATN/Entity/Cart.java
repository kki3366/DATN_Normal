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
	@Column(name = "Cartid")
	int id;
	
	@Column(name = "Name")
	String nameProductCart;
	
	@Column(name = "Price")
	Double priceProductCart;
	
	@Column(name = "Quanlity")
	int quanlityProductCart;
	
	@Column(name = "Img")
	String imgProductCart;
	
	@ManyToOne
	@JoinColumn(name = "Userid")
	users user;
	
	@ManyToOne
	@JoinColumn(name = "idproduct")
	Product product;
	
//	@OneToMany
//	List<Product> products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameProductCart() {
		return nameProductCart;
	}

	public void setNameProductCart(String nameProductCart) {
		this.nameProductCart = nameProductCart;
	}

	public Double getPriceProductCart() {
		return priceProductCart;
	}

	public void setPriceProductCart(Double priceProductCart) {
		this.priceProductCart = priceProductCart;
	}

	public int getQuanlityProductCart() {
		return quanlityProductCart;
	}

	public void setQuanlityProductCart(int quanlityProductCart) {
		this.quanlityProductCart = quanlityProductCart;
	}

	public String getImgProductCart() {
		return imgProductCart;
	}

	public void setImgProductCart(String imgProductCart) {
		this.imgProductCart = imgProductCart;
	}

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
	
	
}
