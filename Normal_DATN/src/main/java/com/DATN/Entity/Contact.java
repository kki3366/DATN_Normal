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
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Contactid")
	int id;
	
	@Column(name = "Name")
	@NotBlank(message = "Bạn chưa nhập Name")
	String name;
	
	@ManyToOne
	@JoinColumn(name = "Userid")
	users user;
	
	@Column(name = "Email")
	@NotBlank(message = "Bạn chưa nhập Email")
	String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	Date date = new Date();
	
	@Column(name = "Status")
	String status;
	
	@Column(name = "Subject")
	@NotBlank(message = "Bạn chưa nhập tiêu đề")
	String subject;
	
	@Column(name = "Contents")
	@NotBlank(message = "Bạn chưa nhập nội dung")
	String Contents;
}
