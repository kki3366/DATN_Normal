package com.DATN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class users implements Serializable{
	@NotBlank(message = "Bạn chưa nhập username")
	@Id
	@Column(name = "Userid")
	String id;
	
	@NotBlank(message = "Bạn chưa nhập mật khẩu")
//	@Pattern(message = "Yếu",regexp = "/^[a-z0-9_-]{6,18}$/")
	@Column(name = "Password")
	String password;
	
	@NotBlank(message = "Bạn chưa nhập họ tên")
	@Column(name = "fullname")
	String fullname;
	@NotBlank(message = "Bạn chưa nhập Phone")
//	@Pattern(message = "Phone chưa đúng định dạng",regexp  = "/^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$/")
	@Column(name = "Phone")
	String phone;
	@NotBlank(message = "Bạn chưa nhập email")
	@Email(message = "Email chưa đúng định dạng")
	@Column(name = "Email")
	String email;
	
	@Column(name = "Activated")
	Boolean activated;
	
	@Column(name = "Admin")
	Boolean admin;
	

//	@OneToMany
//	List<Cart> carts;
//
//	@OneToMany
//	List<Orders> orders;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

//	public List<Cart> getCarts() {
//		return carts;
//	}
//
//	public void setCarts(List<Cart> carts) {
//		this.carts = carts;
//	}
//
//	public List<Orders> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Orders> orders) {
//		this.orders = orders;
//	}
	

}
