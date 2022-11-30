package com.DATN.Controller.NguoiDung;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Entity.Cart;
import com.DATN.Entity.Product;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.ProductRepository;



@Controller
public class singleProduct {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	HttpServletRequest req;
	@Autowired
	CartRepository cartRepository;
	@RequestMapping("/singleProduct/{id}")
	public String form(Model model,@PathVariable("id") Integer id) {
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
		
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
		return "NguoiDung/singleProduct";
		
	}
}
