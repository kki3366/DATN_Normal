package com.DATN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    @Length(min = 6,message = "Mật khẩu phải từ 6 kí tự trở lên")
	@Column(name = "Password")
	String password;
	
	@NotBlank(message = "Bạn chưa nhập họ tên")
	@Column(name = "fullname")
	String fullname;
	@NotBlank(message = "Bạn chưa nhập Phone")
	@Length(min = 10,max=10,message = "Số điện thoại phải từ 6 kí tự trở lên")
	@Column(name = "Phone")
	String phone;
	@NotBlank(message = "Bạn chưa nhập email")
	@Email(message = "Email chưa đúng định dạng")
	@Column(name = "Email")
	String email;
	
	@Column(name = "Activated")
	@NotNull(message = "Bạn chưa chọn trạng thái")
	Boolean activated;
	
	@Column(name = "Admin")
	@NotNull(message = "Bạn chưa chọn vai trò")
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
