package com.DATN.Controller.TaiKhoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Users;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.UserServiceImpl;




@Controller
public class TaiKhoan {

	@Autowired
	UserServiceImpl users;
	@Autowired
	UserRepository u;
	@RequestMapping("/login")
	public String index() {
		return "taiKhoan/login";
	}
	@GetMapping("/dk")
	public String SignUp(Model m) {
		Users acc = new Users();
		m.addAttribute("acc", acc);
		return "taiKhoan/SignUp";
	}
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/dk")
	public String SignUp(Model m,@Validated @ModelAttribute("acc") Users acc, Errors errors,
			@RequestParam("NLpassword") String NLpass) {
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Vui lòng sửa các lỗi sau:");
		}else {
			Integer kt = 0;
			Optional<Users> account = users.findByUsernameService(acc.getId());
			System.err.println(account);
			if (!account.isEmpty()) {
				kt++;
				m.addAttribute("ktTonTai", "User đã tồn tại.");
			}
			if (users.findByEmailService(acc.getEmail()) != null) {
				kt++;
				m.addAttribute("ktEmail", "Email đã tồn tại");
			}
			if (!NLpass.equals(acc.getPassword())) {
				kt++;
				m.addAttribute("ktPass", "Mật khẩu không khớp");
			}
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(acc.getPassword()));
				acc.setActivated(true);
				acc.setAdmin(false);
				users.saveAccountService(acc);
				
				m.addAttribute("tb","Đăng ký thành công");
		}
		}
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
