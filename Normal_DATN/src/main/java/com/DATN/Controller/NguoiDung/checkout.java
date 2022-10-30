package com.DATN.Controller.NguoiDung;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Entity.Cart;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.UserRepository;

@Controller
public class checkout {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpServletRequest req;
	@RequestMapping("/checkout")
	public String form(Model model) {
		List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
		Double tongTien = cartRepository.tongTien(req.getRemoteUser());
		users acc = userRepository.getById(req.getRemoteUser());
		System.err.println(tongTien);		
		model.addAttribute("item", item);
		model.addAttribute("tongTien", tongTien);
		model.addAttribute("acc", acc);

		return "nguoiDung/checkout";
	}
}
