
package com.DATN.Controller.NguoiDung;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Cart;
import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Entity.Product;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.OrdersRepository;
import com.DATN.Repository.ProductRepository;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.CartService;

@Controller
public class checkout {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartService cartService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpServletRequest req;
	@RequestMapping("/checkout")
	public String form(Model model) {
		List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
	
		Double tongTien = cartRepository.tongTien(req.getRemoteUser());
		users acc = userRepository.getById(req.getRemoteUser());
		
		
		model.addAttribute("item", item);
		model.addAttribute("tongTien", tongTien);
		model.addAttribute("acc", acc);
		model.addAttribute("order", new Orders());

		return "nguoiDung/checkout";
		
	}
	@RequestMapping("/addOrder")
	public String add(Orders order ,Model model
//			,@RequestParam("user") String user, @RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("address") String address, 
//			@RequestParam("description") String description, @RequestParam("amount") float amount, @RequestParam("status") String status
			, @RequestParam("address") String address 	) {

		if(address.isEmpty()) {
			List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
			
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			users acc = userRepository.getById(req.getRemoteUser());
			
		
			model.addAttribute("item", item);
			model.addAttribute("tongTien", tongTien);
			model.addAttribute("acc", acc);
			model.addAttribute("order", new Orders());
			model.addAttribute("address", "Vui lòng nhập địa chỉ");
			return "nguoiDung/checkout";
		}else {
			
			ordersRepository.save(order);
			int id = order.getId();
			List<Cart> gio = cartRepository.findByIdUser(req.getRemoteUser());
			
			for(Cart cart:gio) {
				System.err.println("Name la"+cart.getId());
				System.err.println("Id la"+id);
				OrderDetail od = new OrderDetail();
				Product product = productRepository.getById(cart.getProduct().getId());
				Orders ord = ordersRepository.getById(id);
		
				od.setImage(cart.getImgProductCart());
				od.setName(cart.getNameProductCart());
				od.setPrice(cart.getPriceProductCart());
				od.setQuanlity(cart.getQuanlityProductCart());
				od.setProduct(product);
				od.setOrder(ord);
				
				orderDetailRepository.save(od); 
				cartService.clear(cart.getId());
				
			}
			
		
		return "nguoiDung/index";
		}
	}
}
