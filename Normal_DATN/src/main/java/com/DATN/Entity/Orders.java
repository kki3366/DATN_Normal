package com.DATN.Entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	int Id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	Date orderDate = new Date();
	
	@Column(name = "Telephone")
	int telePhone;
	
	@Column(name = "Address")
	String address;
	
	@Column(name = "Amount")
	float amount;
	
	@Column(name = "Description")
	String description;
	
	@Column(name = "Status")
	int status;
	
	@ManyToOne
	@JoinColumn(name = "UserId")
	User user;
}
