package com.DATN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Subcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Subcategoryid")
	int id;
	
	@Column(name = "Name")
	String nameSubCategory;
	
	@OneToMany
	List<Category> categories;
	
	@OneToMany
	List<Product> products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameSubCategory() {
		return nameSubCategory;
	}

	public void setNameSubCategory(String nameSubCategory) {
		this.nameSubCategory = nameSubCategory;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
