

package com.DATN.Controller.NguoiDung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

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
	public String form(Model model) {
		List<Orders> item = orderRepository.findByIdUser(req.getRemoteUser());
		model.addAttribute("item", item);
		return "nguoiDung/orderHistory";
	}
	
	@RequestMapping("/orderHistoryDetail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		List<OrderDetail> item = orderDetailRepository.findByIdOrder(id);
		model.addAttribute("item", item);
		return "nguoiDung/orderHistoryDetail";
	}
}
