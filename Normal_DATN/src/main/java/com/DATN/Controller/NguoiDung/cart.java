package com.DATN.Controller.NguoiDung;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
		
		System.err.println("Tong la"+ tongTien);
		if(tongTien == null) {
			tongTien = (double) 0;
			model.addAttribute("tongTien", tongTien);
		}else {
			model.addAttribute("tongTien", tongTien);
		}
		model.addAttribute("item", item);
	

		return "nguoiDung/cart";
	}
	
	@RequestMapping("/addCartO/{id}")
	public String addCartOutside(@PathVariable("id") Integer id
//			, @RequestParam("NLpassword") String user
			) throws MalformedURLException, URISyntaxException {
//		 URL url = new URL(req.getRequestURL().toString());
//		 System.err.println(url);
//		 String host  = url.getHost();
//		
//		   String userInfo = url.getUserInfo();
//		    URI uri = new URI(host,userInfo,null);
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
	@RequestMapping("/addCartI/{id}")
	public String addCartIn(@PathVariable("id") Integer id
			, @RequestParam("qty") Integer qty
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
			item.setQuanlityProductCart(qty);
//			}
//			item.setQty(qty);
			cart.add(item);
			
		}
	
		return "redirect:/cart";
	}
	

	@RequestMapping("/updateCart")
	public String update( 
			@RequestParam("idcart") Integer id,
			@RequestParam("qty") Integer qty
			) {
	
		System.err.println(id);
		System.err.println(qty);
			cart.update(id,qty);
		
		return "redirect:/cart";
	}
	@RequestMapping("/deleteCart/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return "redirect:/cart";
	}
	@RequestMapping("/clearCart")
	public String clear() {
		List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
		if(item !=null) {
		for(Cart carts:item) {
		cart.clear(carts.getId());
		}
		}
		return "redirect:/cart";
	}
	
}
