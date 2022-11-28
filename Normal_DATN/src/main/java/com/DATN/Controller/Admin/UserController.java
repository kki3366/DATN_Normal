package com.DATN.Controller.Admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import com.DATN.SessionService;
import com.DATN.Entity.Orders;
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
	SessionService session;	
	@Autowired
	HttpServletRequest req;
	int vtPage;
	@RequestMapping("/admin/user")
	public String u(Model m,@RequestParam("p") Optional<Integer> p) {
		List<users> list = user.findAllAccountService();
		int sizeUsers = list.size();
		int size = 5;
		int trang = (int)Math.ceil(sizeUsers/(double) size);
		int sotrang = Integer.parseInt(p.orElse(0)+"");
		System.err.println(sotrang);
		if(sotrang > trang-1) {
			p = Optional.of(0);
			System.err.println("If "+p);
		}else if(sotrang <-1) {
			p = Optional.of(0);
		}
		try {
			Pageable page = PageRequest.of(p.orElse(0), 5);
			Page<users> pageList = u.findAll(page);
			m.addAttribute("page",pageList);
			m.addAttribute("edit",false);
			m.addAttribute("acc",new users());
		}catch (java.lang.IllegalArgumentException e) {
			p = Optional.of(0);
			Pageable page = PageRequest.of(p.orElse(0), 5);
			Page<users> pageList = u.findAll(page);
			m.addAttribute("page",pageList);
			m.addAttribute("edit",false);
			m.addAttribute("acc",new users());
		}
		
//		Pageable page = PageRequest.of(p.orElse(0), 5);
//		Page<users> pageList = u.findAll(page);
//		m.addAttribute("page",pageList);
//		m.addAttribute("edit",false);
//		m.addAttribute("acc",new users());
		
		
		return "Admin/page/user";
	}
	@RequestMapping("/admin/user/find")
	public String find(Model m,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {
		m.addAttribute("acc",new users());
		
		String kwords = kw.orElse(session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		m.addAttribute("keywords", kwords);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<users> page = u.findByKeywords("%"+kwords+"%", pageable);
		m.addAttribute("page", page);
		
//		return "redirect:/admin/user";
		return "Admin/page/user";
	}
	@RequestMapping("/admin/user/new")
	public String New(Model m,@RequestParam("edit") Boolean edit,@RequestParam("p") Optional<Integer> p) {
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<users> pageList = u.findAll(page);
		m.addAttribute("page",pageList);
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
	public String SignUp(Model m,@Validated @ModelAttribute("acc") users acc, Errors errors,@RequestParam("p") Optional<Integer> p) {
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Thêm tài khoản thất bại");
			
		}else {
			Integer kt = 0;
			users account = u.findId(acc.getId());
			if (account != null) {
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
				
				m.addAttribute("tb","Thêm tài khoản thành công");
		}
		}
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<users> pageList = u.findAll(page);
		m.addAttribute("page",pageList);
		m.addAttribute("edit",false);
		return "Admin/page/user";
	}
   
	@RequestMapping("/admin/user/edit")
	public String edit(Model m,@RequestParam("edit") Boolean edit,@RequestParam("user") String User,@RequestParam("p") Optional<Integer> p) {
		users account = u.findId(User);
		m.addAttribute("acc",account);
		m.addAttribute("edit",edit);
	    System.err.println(account.getPassword());
	   
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<users> pageList = u.findAll(page);
		m.addAttribute("page",pageList);
		return "Admin/page/user";
	}
	@PostMapping("/admin/user/update")
	public String update(Model m,@Validated @ModelAttribute("acc") users acc, Errors errors,@RequestParam("p") Optional<Integer> p) {
//		users account = u.findId(User);
//		m.addAttribute("acc",account);
		m.addAttribute("edit",true);
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Sửa tài khoản thất bại");
			
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
				m.addAttribute("ktItMe", "Bạn không thể cập nhật chính bạn");
				
			}
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(acc.getPassword()));
				acc.setActivated(acc.getActivated());
				acc.setAdmin(acc.getAdmin());
				user.saveAccountService(acc);

				m.addAttribute("tb","Sửa tài khoản thành công");
				
		}
		}
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<users> pageList = u.findAll(page);
		m.addAttribute("page",pageList);
		return "Admin/page/user";
	}
	
//	@RequestMapping("/admin/user/status")
//	public String status(Model m,@RequestParam("user") String User,@RequestParam("status") boolean status) {
//		users account = u.findId(User);
//		if(status == false) {
//			account.setActivated(true);
//			u.save(account);
//		}else {
//			account.setActivated(false);
//			u.save(account);
//		}
//		m.addAttribute("acc",account);
//		List<users> list = user.findAllAccountService();
//		m.addAttribute("users",list);
//		return "Admin/page/user";
//	}
}
