package com.DATN.Controller.NguoiDung;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Entity.Cart;
import com.DATN.Entity.Category;
import com.DATN.Entity.Product;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.ProductRepository;


@Controller
public class index {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categorytRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	HttpServletRequest req;
	@RequestMapping("/index")
	public String form(Model model) {
		if(req.getRemoteUser() != null) {
		List<Cart> ite = cartRepository.findByIdUser(req.getRemoteUser());
		Double tongTien = cartRepository.tongTien(req.getRemoteUser());
		if(tongTien == null) {
			tongTien = (double) 0;
			model.addAttribute("tongTien", tongTien);
		}else {
			model.addAttribute("tongTien", tongTien);
		}
		model.addAttribute("size", ite.size());
		}
		List<Product> item = productRepository.findAll();
		List<Category> category = categorytRepository.findAll();
		model.addAttribute("item", item);
		model.addAttribute("category", category);
		return "nguoiDung/index";
	}
}
