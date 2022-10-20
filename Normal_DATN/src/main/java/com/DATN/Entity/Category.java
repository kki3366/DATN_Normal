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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Categoryid")
	int id;
	
	@NotEmpty(message = "Không được để trống tên category")
	@Column(name = "Name")
	String nameCategory;
	
	@JsonBackReference
	@OneToMany
	List<Product> products;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNameCategory() {
		return nameCategory;
	}


	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

}
