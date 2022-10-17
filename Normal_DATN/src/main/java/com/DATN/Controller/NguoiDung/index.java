package com.DATN.Controller.NguoiDung;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;






@Controller
public class index {
//	@Autowired
//	ProductRepository productRepository;

	@RequestMapping("/index")
	public String form(Model model) {
//		List<Product> item = productRepository.findAll();
//		model.addAttribute("item", item);
		return "nguoiDung/index";
	}
}
