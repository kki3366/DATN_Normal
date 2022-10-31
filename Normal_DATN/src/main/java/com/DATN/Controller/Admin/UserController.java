package com.DATN.Controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		return "Admin/page/user";
	}
	
}
