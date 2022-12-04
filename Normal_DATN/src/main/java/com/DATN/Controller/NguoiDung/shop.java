package com.DATN.Controller.NguoiDung;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.SessionService;
import com.DATN.Entity.Cart;
import com.DATN.Entity.Category;
import com.DATN.Entity.Orders;
import com.DATN.Entity.Product;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.ProductRepository;



@Controller
public class shop {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SessionService session;	
	@Autowired
	HttpServletRequest req;
	@Autowired
	CartRepository cartRepository;
	@RequestMapping("/shop/{id}")
	public String form(Model model, @PathVariable("id") Integer id,@RequestParam("subcategory") Integer subcategory,
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		
		if(req.getRemoteUser() != null) {
			List<Cart> ite = cartRepository.findByIdUser(req.getRemoteUser());
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			if(tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			}else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("size", ite.size());
			}
		
		List<Category> item = categoryRepository.findAll();
		
		model.addAttribute("category", item);
		model.addAttribute("item", item);
		
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(6);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<Product> resultPage = productRepository.findByShop(id,subcategory,pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages >0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage +2,totalPages);
			
			if(totalPages >6) {
				if(end == totalPages) {
					start = end -6;
				}else if(start == 1) {
					end = start +6;
				}
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers",pageNumber);
		}
		model.addAttribute("shopPage",resultPage);
		
		
		model.addAttribute("subcategory", subcategory);
		if(resultPage == null) {
			model.addAttribute("message", "Hiện không có sản phẩm");
		}
		Category cate = categoryRepository.findById(id).get();
		model.addAttribute("cate", cate);
		
		return "nguoiDung/shop";
	
	}
	@RequestMapping("/shop/find")
	public String find(Model model,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		if(req.getRemoteUser() != null) {
			List<Cart> ite = cartRepository.findByIdUser(req.getRemoteUser());
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			if(tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			}else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("size", ite.size());
			}
		
	List<Category> item = categoryRepository.findAll();
		
		model.addAttribute("category", item);
		
		String kwords = kw.orElse(session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(6);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<Product> resultPage = productRepository.findByKeywords("%"+kwords+"%", pageable);
		if(resultPage.isEmpty()) {
			model.addAttribute("NoProduct", true);
		}
		int totalPages = resultPage.getTotalPages();
		if(totalPages >0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage +2,totalPages);
			
			if(totalPages >6) {
				if(end == totalPages) {
					start = end -6;
				}else if(start == 1) {
					end = start +6;
				}
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers",pageNumber);
		}
		model.addAttribute("shopFindPage",resultPage);
	
		model.addAttribute("keywords", kwords);
		
		return "nguoiDung/shopFind";
	}
	@RequestMapping("/shop")
	public String form2(Model model, @RequestParam("subcategory") Integer subcategory,
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		if(req.getRemoteUser() != null) {
			List<Cart> ite = cartRepository.findByIdUser(req.getRemoteUser());
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			if(tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			}else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("size", ite.size());
			}
		
		List<Category> item = categoryRepository.findAll();
		
		model.addAttribute("category", item);
		model.addAttribute("item", item);
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(6);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<Product> resultPage = productRepository.findByShopNN(subcategory,pageable);
		int totalPages = resultPage.getTotalPages();
		if(totalPages >0) {
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage +2,totalPages);
			
			if(totalPages >6) {
				if(end == totalPages) {
					start = end -6;
				}else if(start == 1) {
					end = start +6;
				}
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers",pageNumber);
		}
		model.addAttribute("shopNPage",resultPage);
		
		model.addAttribute("subcategory", subcategory);
		if(resultPage == null) {
			model.addAttribute("message", "Hiện không có sản phẩm");
		}

		
		return "nguoiDung/shopN";
	
	}
}

