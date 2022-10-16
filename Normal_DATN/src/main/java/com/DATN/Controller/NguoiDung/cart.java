package com.DATN.Controller.NguoiDung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Cart;
import com.DATN.Entity.Product;
import com.DATN.Entity.Users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.ProductRepository;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.CartService;





@Controller
public class cart {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartService cart;

	@RequestMapping("/cart")
	public String form(Model model) {
		List<Cart> item = cartRepository.findAll();
		model.addAttribute("item", item);
		return "nguoiDung/cart";
	}
	
	@RequestMapping("/addCart/{id}")
	public String add(@PathVariable("id") Integer id
//			, @RequestParam("NLpassword") String user
			) {
		Product product = productRepository.getById(id);
		Users users = userRepository.getById("BaoDG");
	
		if(product != null) {
			Cart item = new Cart();
			
			item.setProduct(product);
			item.setNameProductCart(product.getName());
			item.setPriceProductCart(product.getPrice());
			item.setImgProductCart(product.getImage());
			item.setUser(users);
			
		
//			if(qty == 0) {
			item.setQuanlityProductCart(1);
//			}
//			item.setQty(qty);
			cart.add(item);
			
		}
	
		return "redirect:/cart";
	}
}