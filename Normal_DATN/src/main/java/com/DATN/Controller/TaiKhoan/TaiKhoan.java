package com.DATN.Controller.TaiKhoan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaiKhoan {

	@RequestMapping("/login")
	public String index() {
		return "taiKhoan/login";
	}
	@RequestMapping("/dk")
	public String SignUp() {
		return "taiKhoan/SignUp";
	}
	@RequestMapping("/fogetPass")
	public String fogetPass() {
		return "taiKhoan/FogetPass";
	}
	@RequestMapping("/changePass")
	public String changePass() {
		return "taiKhoan/ChangePass";
	}
	@RequestMapping("/editProfile")
	public String editProfile() {
		return "taiKhoan/EditProfile";
	}
}
