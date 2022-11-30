
package com.DATN.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
@Entity
//@Table(name = "OrderDetails")
@Table(name = "Orderdetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Orderdetailid")
	int id;
	
	
	@Column(name = "Name")
	String name;
	
	@Column(name = "Price")
	Double price;
	
	@Column(name = "Quanlity")
	int quanlity;
	
	@Column(name = "name_cate")
	String namecate;
	
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Orders order;

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

	public int getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}

	public String getNamecate() {
		return namecate;
	}

	public void setNamecate(String namecate) {
		this.namecate = namecate;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	
	
	
}

