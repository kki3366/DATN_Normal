package com.DATN.Controller.Admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.SessionService;
import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Entity.users;
import com.DATN.Repository.OrderDetailRepository;
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
	OrderDetailRepository orderDetailRepository;
	@Autowired
	SessionService session;	
	@RequestMapping("/admin/order")
	public String form(Model m,	@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<Orders> resultPage = ordersRepository.findAll(pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages >0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage +2,totalPages);
			
			if(totalPages >5) {
				if(end == totalPages) {
					start = end -5;
				}else if(start == 1) {
					end = start +5;
				}
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			
			m.addAttribute("pageNumbers",pageNumber);
		}
		m.addAttribute("OrderPage",resultPage);
		
		
		m.addAttribute("edit",false);

		m.addAttribute("order",new Orders());
		return "Admin/page/order";
	}
	@PostMapping("/admin/order/update")
	public String add(Model m,@Validated @ModelAttribute("order") Orders order
			,	@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s ){
	
	
	order.setStatus(order.getStatus());
	ordersRepository.save(order);
	int currentPage = p.orElse(0);
	int pagesize = s.orElse(5);
	Pageable pageable = PageRequest.of(currentPage, pagesize);
	Page<Orders> resultPage = ordersRepository.findAll(pageable);
	int totalPages = resultPage.getTotalPages();
	if(totalPages >0) {
		int start = Math.max(1,currentPage-2);
		int end = Math.min(currentPage +2,totalPages);
		
		if(totalPages >5) {
			if(end == totalPages) {
				start = end -5;
			}else if(start == 1) {
				end = start +5;
			}
		}
		List<Integer> pageNumber = IntStream.rangeClosed(start,end)
				.boxed()
				.collect(Collectors.toList());
		
		m.addAttribute("pageNumbers",pageNumber);
	}
	m.addAttribute("OrderPage",resultPage);
	m.addAttribute("tb","Sửa đơn hàng thành công");
	return "Admin/page/order";
		}
	
	@RequestMapping("/admin/order/edit")
	public String edit(Model m,@RequestParam("edit") Boolean edit,@RequestParam("order") Integer Order
			,	@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {
		Orders ord = ordersRepository.getById(Order);
		m.addAttribute("order",ord);
		m.addAttribute("edit",edit);
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<Orders> resultPage = ordersRepository.findAll(pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages >0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage +2,totalPages);
			
			if(totalPages >5) {
				if(end == totalPages) {
					start = end -5;
				}else if(start == 1) {
					end = start +5;
				}
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			
			m.addAttribute("pageNumbers",pageNumber);
		}
		m.addAttribute("OrderPage",resultPage);
		return "Admin/page/order";
	}
	@RequestMapping("/admin/order/find")
	public String find(Model model,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {

		
		String kwords = kw.orElse(session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<Orders> resultPage = ordersRepository.findByKeywords("%"+kwords+"%", pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages >0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage +2,totalPages);
			
			if(totalPages >5) {
				if(end == totalPages) {
					start = end -5;
				}else if(start == 1) {
					end = start +5;
				}
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers",pageNumber);
		}
		model.addAttribute("OrderPage",resultPage);
		
		model.addAttribute("order",new Orders());
		model.addAttribute("keywords", kwords);
		return "Admin/page/order";
	}
	@RequestMapping("/admin/orderDetail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		List<OrderDetail> item = orderDetailRepository.findByIdOrder(id);
		model.addAttribute("item", item);
		return "Admin/page/orderDetail";
	}
}
	
