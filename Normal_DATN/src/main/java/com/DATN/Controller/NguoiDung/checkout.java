
package com.DATN.Controller.NguoiDung;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("size", item.size());
		if(tongTien == null) {
			tongTien = (double) 0;
			model.addAttribute("tongTien", tongTien);
		}else {
			model.addAttribute("tongTien", tongTien);
		}
		model.addAttribute("acc", acc);
		model.addAttribute("order", new Orders());

		return "nguoiDung/checkout";
		
	}
	@RequestMapping("/addOrder")
	public String add( Orders order ,Model model) {
		if(order.getPhone().isEmpty() || order.getAddress().isEmpty() || order.getPhone().length()>11 || order.getPhone().length()<9  ) {

			List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
			
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			users acc = userRepository.getById(req.getRemoteUser());
			
		
			model.addAttribute("item", item);
			model.addAttribute("size", item.size());
			if(tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			}else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("acc", acc);
			model.addAttribute("order", new Orders());
			model.addAttribute("loiphone", "Vui lòng nhập số điện thoại");
			if(order.getPhone().length()>11 || order.getPhone().length()<9  ) {
				model.addAttribute("loiphone", "Vui lòng nhập đúng số điện thoại");
			}
		
			if(order.getAddress().isEmpty()) {	
				model.addAttribute("loiaddress", "Vui lòng nhập địa chỉ");
			}
			
			return "nguoiDung/checkout";
//			return "redirect:/checkout";
		}else {
			order.setStatus("Đã đặt");
			ordersRepository.save(order);
			int id = order.getId();
			List<Cart> gio = cartRepository.findByIdUser(req.getRemoteUser());
			
			for(Cart cart:gio) {
				OrderDetail od = new OrderDetail();
				Product product = productRepository.getById(cart.getProduct().getId());
				Orders ord = ordersRepository.getById(id);
				od.setName(cart.getNameProductCart());
				od.setPrice(cart.getPriceProductCart());
				od.setQuanlity(cart.getQuanlityProductCart());
				od.setOrder(ord);
				//
				orderDetailRepository.save(od); 
				if(product.getQuantity()==0) {
					product.setAvailable(false);
					productRepository.save(product);					
				}else {
				product.setQuantity(product.getQuantity()-cart.getQuanlityProductCart());
			
				productRepository.save(product);	
				}
				cartService.clear(cart.getId());
				
			
			}
		
	return "redirect:/index";
	
		}
	}	
}
