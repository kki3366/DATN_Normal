package com.DATN.Controller.Admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DATN.Entity.users;
import com.DATN.Service.UserServiceImpl;



@Controller
public class UserController {

	@Autowired
	UserServiceImpl user;
	@GetMapping("/admin/user")
	public String u(Model m) {
		List<users> list = user.findAllAccountService();
		m.addAttribute("users",list);
		m.addAttribute("acc",new users());
		return "Admin/page/user";
	}
	
	@PostMapping("/admin/user/add")
	public String add(Model m,@Validated @ModelAttribute("acc") users acc, Errors errors) {
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Vui lòng sửa các lỗi sau:");
			
		}else {
			Integer kt = 0;
			Optional<users> account = user.findByUsernameService(acc.getId());
			System.err.println(account);
//			if(acc.getId().isEmpty()) {
//				kt++;
//				m.addAttribute("ktTonTai", "User đã tồn tại.");
//			}
			if (!account.isEmpty()) {
				kt++;
				m.addAttribute("ktTonTai", "User đã tồn tại.");
			}
			if (user.findByEmailService(acc.getEmail()) != null) {
				kt++;
				m.addAttribute("ktEmail", "Email đã tồn tại");
			}
			if (user.findByPhoneService(acc.getPhone()) != null) {
				kt++;
				m.addAttribute("ktPhone", "Phone đã tồn tại");
			}
			
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(acc.getPassword()));
				acc.setActivated(true);
				acc.setAdmin(false);
				user.saveAccountService(acc);
				
				m.addAttribute("tb","Đăng ký thành công");
		}
		}
		this.u(m);
		return "Admin/page/user";
		}
	
}
