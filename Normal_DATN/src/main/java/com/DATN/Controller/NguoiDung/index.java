package com.DATN.Controller.NguoiDung;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Entity.Category;
import com.DATN.Entity.Product;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.ProductRepository;


@Controller
public class index {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categorytRepository;

	@RequestMapping("/index")
	public String form(Model model) {
		List<Product> item = productRepository.findAll();
		List<Category> category = categorytRepository.findAll();
		model.addAttribute("item", item);
		model.addAttribute("category", category);
		return "nguoiDung/index";
	}
}
