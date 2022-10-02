//package com.DATN.Entity;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "Products")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Product {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "Id")
//	int id;
//	
//	@Column(name = "Name")
//	String nameProduct;
//	
//	@Column(name = "Price")
//	float priceProduct;
//	
//	@Column(name = "Image")
//	String imgProduct;
//	
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "ProductDate")
//	Date productDate = new Date();
//	
//	
//	@Column(name = "Available")
//	boolean available;
//	
//	@Column(name = "Quantity")
//	int quantity;
//	
//	
//	@Column(name = "Description")
//	String description;
//	
//	@Column(name = "Discount")
//	float discount;
//	
//	
//	@Column(name = "ViewCount")
//	int viewCount;
//
//	
//	@Column(name = "Special")
//	boolean special;
//	
//	
//	@ManyToOne
//	@JoinColumn(name = "CategoryId")
//	Category category;
//}
