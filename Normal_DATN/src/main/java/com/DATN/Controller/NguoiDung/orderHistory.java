

package com.DATN.Controller.NguoiDung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.OrdersRepository;


@Controller
public class orderHistory {
	@Autowired
	OrdersRepository orderRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	HttpServletRequest req;
	@RequestMapping("/orderHistory")
	public String form(Model model, @RequestParam("field") Optional<String> field) {
		Sort sort = Sort.by(Direction.DESC,field.orElse("orderDate"));
		//
		List<Orders> item = orderRepository.findByIdUser(req.getRemoteUser(),sort);
		model.addAttribute("item", item);
		return "nguoiDung/orderHistory";
	}
	
	@RequestMapping("/orderHistoryDetail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		List<OrderDetail> item = orderDetailRepository.findByIdOrder(id);
		model.addAttribute("item", item);
		return "nguoiDung/orderHistoryDetail";
	}
	@RequestMapping("/orderHistory/{id}")
	public String status(Model model, @PathVariable("id") Integer id) {
		Orders order = orderRepository.getById(id);
		order.setStatus("Đã hủy");
		orderRepository.save(order);
		return "redirect:/orderHistory";
	}
}
