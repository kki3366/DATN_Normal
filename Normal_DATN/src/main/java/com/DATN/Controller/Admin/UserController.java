package com.DATN.Controller.Admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.users;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.UserServiceImpl;



@Controller
public class UserController {

	@Autowired
	UserServiceImpl user;
	@Autowired
	UserRepository u;
	@Autowired
	HttpServletRequest req;
	@GetMapping("/admin/user")
	public String u(Model m,@RequestParam("edit") Boolean edit) {
		List<users> list = user.findAllAccountService();
		m.addAttribute("users",list);
		m.addAttribute("edit",edit);
		m.addAttribute("acc",new users());
		return "Admin/page/user";
	}
	@GetMapping("/admin/user/add")
	public String SignUp(Model m) {
		users acc = new users();
		acc.setAdmin(false);
		user.saveAccountService(acc);
		m.addAttribute("acc", acc);
		return "taiKhoan/SignUp";
	}
	@PostMapping("/admin/user/add")
	public String SignUp(Model m,@Validated @ModelAttribute("acc") users acc, Errors errors) {
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Thêm tài khoản thất bại");
			System.err.println("VT:"+acc.getAdmin());
		}else {
			Integer kt = 0;
			users account = u.findId(acc.getId());
			if (account != null) {
				kt++;
				System.err.println("User ton tai");
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
				
				m.addAttribute("tb","Thêm tài khoản thành công");
		}
		}
		List<users> list = user.findAllAccountService();
		m.addAttribute("users",list);
		return "Admin/page/user";
	}
   
	@RequestMapping("/admin/user/edit")
	public String edit(Model m,@RequestParam("edit") Boolean edit,@RequestParam("user") String User) {
		users account = u.findId(User);
		m.addAttribute("acc",account);
		m.addAttribute("edit",edit);
		System.err.println("edit:"+edit);
		List<users> list = user.findAllAccountService();
		m.addAttribute("users",list);
		return "Admin/page/user";
	}
	@RequestMapping("/admin/user/update")
	public String update(Model m,@Validated @ModelAttribute("acc") users acc, Errors errors) {
//		users account = u.findId(User);
//		m.addAttribute("acc",account);
		m.addAttribute("edit",true);
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Sửa tài khoản thất bại");
			System.err.println("VT:"+acc.getAdmin());
		}else {
			Integer kt = 0;
			users account = u.findId(acc.getId());
			
			if (user.findByEmailService(acc.getEmail()) != null) {
				
				users x = u.findByEmailAndUser(acc.getId(),acc.getEmail());
				if(x == null) {
					kt++;
					m.addAttribute("ktEmail", "Email đã tồn tại");
				}
				
			}
			
			if (user.findByPhoneService(acc.getPhone()) != null) {
				users x = u.findByPhoneAndUser(acc.getId(),acc.getPhone());
				if(x == null) {
					kt++;
					m.addAttribute("ktPhone", "Phone đã tồn tại");
				}
			}
			if(acc.getId().equals(req.getRemoteUser())) {
				kt++;
				m.addAttribute("ktPhone", "Bạn không thể cập nhật chính bạn");
				System.err.println("Bạn không thể cập nhật chính bạn");
			}
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(acc.getPassword()));
				acc.setActivated(true);
				acc.setAdmin(false);
				user.saveAccountService(acc);
//				m.addAttribute("acc",acc);
				m.addAttribute("tb","Sửa tài khoản thành công");
				
		}
		}
		List<users> list = user.findAllAccountService();
		m.addAttribute("users",list);
		return "Admin/page/user";
	}
}
