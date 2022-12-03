package com.DATN.Controller.NguoiDung;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.SUM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.DATN.Entity.Cart;
import com.DATN.Entity.Category;
import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Product;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.ProductRepository;


@Controller
public class index {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderDetailRepository orderDetaiRep;
	@Autowired
	CategoryRepository categorytRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	HttpServletRequest req;
	@SuppressWarnings("unchecked")
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
		Pageable pageable = PageRequest.of(0,5,Sort.by(Direction.DESC,"date"));
		
		Page<Product> item = productRepository.findAll(pageable);
		List<Category> category = categorytRepository.findAll();
		model.addAttribute("item", item);
		model.addAttribute("category", category);
		
        List<Product> p = null;
		Pageable pageableBanChay = PageRequest.of(0,5);
		List<String> BanChay = orderDetaiRep.BanChayNhat();
		for (String orderDetail : BanChay) {
			List<Product> spbanchay = productRepository.findByName(orderDetail);
			model.addAttribute("spbanchay", spbanchay);
			p.add((Product) spbanchay);
		}
		
		
	
		return "nguoiDung/index";
	}
}
