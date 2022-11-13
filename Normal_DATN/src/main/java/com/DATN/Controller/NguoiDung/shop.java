package com.DATN.Controller.NguoiDung;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Category;
import com.DATN.Entity.Product;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.ProductRepository;



@Controller
public class shop {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/shop/{id}")
	public String form(Model model, @PathVariable("id") Integer id,@RequestParam("subcategory") Integer subcategory,
			@RequestParam("p") Optional<Integer> p) {
		List<Category> item = categoryRepository.findAll();
		model.addAttribute("item", item);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> page = productRepository.findByShop(id,subcategory,pageable);
		model.addAttribute("page", page);
		model.addAttribute("subcategory", subcategory);
		if(page == null) {
			model.addAttribute("message", "Hiện không có sản phẩm");
		}
		Category cate = categoryRepository.findById(id).get();
		model.addAttribute("cate", cate);
		
		return "nguoiDung/shop";
	//	
	}
}

