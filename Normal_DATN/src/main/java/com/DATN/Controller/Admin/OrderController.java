package com.DATN.Controller.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.SessionService;
import com.DATN.Entity.Orders;
import com.DATN.Entity.users;
import com.DATN.Repository.OrdersRepository;
import com.DATN.Service.OrderService;
import com.DATN.Service.OrderServiceImpl;





@Controller
public class OrderController {
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	OrderServiceImpl orderServiceImpl;
	@Autowired
	SessionService session;	
	@RequestMapping("/admin/order")
	public String form(Model m) {
		List<Orders> orderList = orderServiceImpl.findAll();
		m.addAttribute("orderList",orderList);

		m.addAttribute("order",new Orders());
		return "Admin/page/order";
	}
	@RequestMapping("/admin/order/add")
	public String add(Model m,@Validated @ModelAttribute("order") Orders order, Errors errors) {
		if(errors.hasErrors()) {
			
		}
		return "redirect:/admin/order";
		}
	
	@RequestMapping("/admin/order/edit")
	public String edit(Model m,@RequestParam("edit") Boolean edit,@RequestParam("order") Integer Order) {
		Orders ord = ordersRepository.getById(Order);
		m.addAttribute("order",ord);
		m.addAttribute("edit",edit);
		List<Orders> orderList = orderServiceImpl.findAll();
		m.addAttribute("orderList",orderList);
		return "Admin/page/order";
	}
	@RequestMapping("/admin/order/find")
	public String find(Model model,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {

		
		String kwords = kw.orElse(session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<Orders> page = ordersRepository.findByKeywords("%"+kwords+"%", pageable);
		model.addAttribute("page", page);
		return "redirect:/admin/order";
	}
}
	
