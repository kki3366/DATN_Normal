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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNameProductCart() {
		return nameProductCart;
	}

	public void setNameProductCart(String nameProductCart) {
		this.nameProductCart = nameProductCart;
	}

	public float getPriceProductCart() {
		return priceProductCart;
	}

	public void setPriceProductCart(float priceProductCart) {
		this.priceProductCart = priceProductCart;
	}

	public int getQuanlityProductCart() {
		return quanlityProductCart;
	}

	public void setQuanlityProductCart(int quanlityProductCart) {
		this.quanlityProductCart = quanlityProductCart;
	}

	public int getImgProductCart() {
		return imgProductCart;
	}

	public void setImgProductCart(int imgProductCart) {
		this.imgProductCart = imgProductCart;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
