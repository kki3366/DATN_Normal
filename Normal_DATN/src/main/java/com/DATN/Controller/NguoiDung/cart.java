package com.DATN.Controller.NguoiDung;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Cart;
import com.DATN.Entity.Product;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.ProductRepository;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.CartService;





@Controller
public class cart {
	@Autowired
	HttpServletRequest req;
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
		List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
		Double tongTien = cartRepository.tongTien(req.getRemoteUser());
		System.err.println(tongTien);		
		model.addAttribute("item", item);
		model.addAttribute("tongTien", tongTien);

		return "nguoiDung/cart";
	}
	
	@RequestMapping("/addCart/{id}")
	public String add(@PathVariable("id") Integer id
//			, @RequestParam("NLpassword") String user
			) {
		Product product = productRepository.getById(id);
		users users = userRepository.getById(req.getRemoteUser());
	
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
	

	@RequestMapping("/updateCart")
	public String update( 
			@RequestParam("idcart") Integer id,
			@RequestParam("qty") Integer qty,
			HttpServletRequest req) {
	
		System.err.println(id);
//		System.out.println("Nhu con caccccccccccccccccccccccccccccccccccccccccccccccccc");
			cart.update(3,10);
		
		return "redirect:/cart";
	}
	@RequestMapping("/deleteCart/{id}")
	public String remove(@PathVariable("id") Integer id) {
		System.out.println("Nhu con caccccccccccccccccccccccccccccccccccccccccccccccccc");
		cart.remove(id);
		return "redirect:/cart";
	}
	@RequestMapping("/clearCart")
	public String clear() {
		cart.clear();
		return "redirect:/cart";
	}
	
}
