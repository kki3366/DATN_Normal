
package com.DATN.Controller.NguoiDung;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Entity.Cart;
import com.DATN.Entity.Category;
import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Entity.Product;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.OrdersRepository;
import com.DATN.Repository.ProductRepository;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.CartService;
import com.DATN.Service.ProductService;
import com.DATN.Unit.FileUploadUtil;

@Controller
@Validated
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
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	CategoryRepository categoryRepository;
	
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
	public String add( Orders order ,Model model) throws IOException {
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
			if(order.getPhone().isEmpty()) {		
			model.addAttribute("loiphone", "Vui lòng nhập số điện thoại");
			}
			if(order.getPhone().length()>11 || order.getPhone().length()<9  ) {
				model.addAttribute("loiphone", "Vui lòng nhập đúng số điện thoại");
			}
		
			if(order.getAddress().isEmpty()) {	
				model.addAttribute("loiaddress", "Vui lòng nhập địa chỉ");
			}
			
			return "nguoiDung/checkout";

		}else {
			
			order.setStatus("Đã đặt");
			ordersRepository.save(order);
			int id = order.getId();
			List<Cart> gio = cartRepository.findByIdUser(req.getRemoteUser());
			
			for(Cart cart:gio) {
				OrderDetail od = new OrderDetail();
				Product product = productRepository.findById(cart.getProduct().getId()).get();
				Orders ord = ordersRepository.getById(id);
				Category cate = categoryRepository.getById(product.getCategory().getId());

				
				FileUploadUtil file = new FileUploadUtil();
				file.historyImageProduct(cart.getImgProductCart(), app);
				
				od.setName(cart.getNameProductCart());
				od.setImage(cart.getImgProductCart());
				od.setPrice(cart.getPriceProductCart());
				od.setQuanlity(cart.getQuanlityProductCart());
				od.setNamecate(cate.getNameCategory());
				od.setOrder(ord);
				
				orderDetailRepository.save(od); 
				int total = product.getQuantity() -cart.getQuanlityProductCart();

				if(total <= 0) {
					product.setAvailable(false);
					productRepository.save(product);
				}else {
					product.setQuantity(total);
					productRepository.save(product);
				}

				productRepository.save(product);	
				cartService.clear(cart.getId());
				
			
			}
		
	return "redirect:/index";
	
		}
	}	
}
