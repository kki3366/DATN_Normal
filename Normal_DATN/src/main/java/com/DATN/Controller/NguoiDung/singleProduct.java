package com.DATN.Controller.NguoiDung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Entity.Product;
import com.DATN.Repository.ProductRepository;



@Controller
public class singleProduct {
	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/singleProduct/{id}")
	public String form(Model model,@PathVariable("id") Integer id) {

		
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
		return "NguoiDung/singleProduct";
		
	}
}
