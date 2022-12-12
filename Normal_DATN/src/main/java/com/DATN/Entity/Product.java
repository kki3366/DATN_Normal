package com.DATN.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Productid")
	int id;
	
	@NotEmpty(message = "Không được để trống tên sản phẩm")
	@Size(max = 100, message = "Tên sản phẩm tối là 100 kí tự")
	@Column(name = "Name")
	String name;
	
	@NotNull(message = "Không được để trống giá sản phẩm")
	@Min(value = 40000, message = "Giá nhỏ nhất phải là 40.000 đồng")
	@Column(name = "Price")
	Double price;
	
	@Column(name = "Image")
	String image;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	Date date = new Date();
	
	
	@Column(name = "Available")
	boolean available = true;
	
	@NotNull(message = "Không được để trống số lượng")
	@Min(value = 1, message = "Số lượng tối thiểu là 1")
	@Column(name = "Quantity")
	Integer quantity;
	
	@NotEmpty(message = "Không được để trống mô tả sản phẩm")
	@Column(name = "Description")
	String description;
	

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = SubCategory.class)
	@JoinColumn(name = "Subcategoryid")
	SubCategory subcategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImgage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}
	
	
}
