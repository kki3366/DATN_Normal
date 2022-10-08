package com.DATN.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Products")
//@AllArgsConstructor
//@NoArgsConstructor

public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	int id;
	
	@Column(name = "Name")
	String name;
	
	@Column(name = "Price")
	float priceProduct;
	
	@Column(name = "Image")
	String imgProduct;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	Date productDate = new Date();
	
	
	@Column(name = "Available")
	boolean available;
	
	@Column(name = "Quantity")
	int quantity;
	
	
	@Column(name = "Description")
	String description;
	
	@Column(name = "Discount")
	float discount;
	
	
	@Column(name = "Viewcount")
	int viewCount;

	
	@Column(name = "Special")
	boolean special;
	
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "Subcategoryid")
	SubCategory subcategory;
}
