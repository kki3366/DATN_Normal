package com.DATN.Controller.Admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.SessionService;
import com.DATN.Entity.Orders;
import com.DATN.Entity.users;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.UserServiceImpl;





@Controller
public class UserController {

	@Autowired
	UserServiceImpl user;
	@Autowired
	UserRepository u;
	@Autowired
	SessionService session;	
	@Autowired
	HttpServletRequest req;
	int vtPage;
    
	@RequestMapping("/admin/user")
	public String u(Model m,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<users> resultPage = u.findAll(pageable);
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
		m.addAttribute("userPage",resultPage);
		m.addAttribute("edit",false);
	m.addAttribute("acc",new users());
		return "Admin/page/user";
	}
	@RequestMapping("/admin/user/find")
	public String find(Model m,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		m.addAttribute("acc",new users());
		
		String kwords = kw.orElse(session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		m.addAttribute("keywords", kwords);
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<users> resultPage = u.findByKeywords("%"+kwords+"%", pageable);
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
		m.addAttribute("userPage",resultPage);
	
		

		return "Admin/page/user";
	}
	@RequestMapping("/admin/user/new")
	public String New(Model m,@RequestParam("edit") Boolean edit,@RequestParam("p") Optional<Integer> p) {
		
		return "redirect:/admin/user";
	}
	@GetMapping("/admin/user/add")
	public String SignUp(Model m) {
		users acc = new users();
		acc.setAdmin(false);
		user.saveAccountService(acc);
		m.addAttribute("acc", acc);
		return "taiKhoan/SignUp";
	}
	@PostMapping("/admin/user/add")
	public String SignUp(Model m,@RequestParam("pass") String pass,@Validated @ModelAttribute("acc") users acc, Errors errors,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Th??m t??i kho???n th???t b???i");
			
		}else {
			Integer kt = 0;
			users account = u.findId(acc.getId());
			if (account != null) {
				kt++;
				
				m.addAttribute("ktTonTai", "username ???? t???n t???i");
			}
			if (user.findByEmailService(acc.getEmail()) != null) {
				kt++;
				m.addAttribute("ktEmail", "Email ???? t???n t???i");
			}
			if (user.findByPhoneService(acc.getPhone()) != null) {
				kt++;
				m.addAttribute("ktPhone", "Phone ???? t???n t???i");
			}
			
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(pass));
				acc.setActivated(true);
				acc.setAdmin(false);
				user.saveAccountService(acc);
				
				m.addAttribute("tb","Th??m t??i kho???n th??nh c??ng");
		}
		}
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<users> resultPage = u.findAll(pageable);
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
		m.addAttribute("userPage",resultPage);
		m.addAttribute("edit",false);
		return "Admin/page/user";
	}
   
	@RequestMapping("/admin/user/edit")
	public String edit(Model m,@RequestParam("edit") Boolean edit,@RequestParam("user") String User,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		users account = u.findId(User);
		m.addAttribute("acc",account);
		
		m.addAttribute("edit",edit);
	    
	   
	    int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<users> resultPage = u.findAll(pageable);
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
		m.addAttribute("userPage",resultPage);
		return "Admin/page/user";
	}
	@RequestMapping("/admin/user/lock")
	public String lock(Model m,@RequestParam("pass") String pass, @ModelAttribute("acc") users acc,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		if(acc.getId().equals(req.getRemoteUser())) {
			
			m.addAttribute("ktItMe", "B???n kh??ng th??? kh??a t??i kho???n c???a m??nh");
			
		}else {
			acc.setActivated(false);
			BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
			acc.setPassword(pe.encode(pass));
			u.save(acc);
			m.addAttribute("tb","T??i kho???n b??? ???? kh??a");
		}
		
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<users> resultPage = u.findAll(pageable);
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
		m.addAttribute("userPage",resultPage);
		m.addAttribute("edit",true);
		return "Admin/page/user";
	}
	@PostMapping("/admin/user/update")
	public String update(Model m,@RequestParam("pass") String pass,@Validated @ModelAttribute("acc") users acc, Errors errors,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
//		users account = u.findId(User);
//		m.addAttribute("acc",account);
		m.addAttribute("edit",true);
		
		if(errors.hasErrors()) {
			m.addAttribute("tb", "S???a t??i kho???n th???t b???i");
			
		}else {
			BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
			acc.setPassword(pe.encode(pass));
			Integer kt = 0;
			users account = u.findId(acc.getId());
			
			if (user.findByEmailService(acc.getEmail()) != null) {
				
				users x = u.findByEmailAndUser(acc.getId(),acc.getEmail());
				if(x == null) {
					kt++;
					m.addAttribute("ktEmail", "Email ???? t???n t???i");
				}
				
			}
			
			if (user.findByPhoneService(acc.getPhone()) != null) {
				users x = u.findByPhoneAndUser(acc.getId(),acc.getPhone());
				if(x == null) {
					kt++;
					m.addAttribute("ktPhone", "Phone ???? t???n t???i");
				}
			}
			if(acc.getId().equals(req.getRemoteUser())) {
				kt++;
				m.addAttribute("ktItMe", "B???n kh??ng th??? c???p nh???t t??i kho???n c???a m??nh");
				
			}
			if(kt==0) {
				
				
				
				acc.setActivated(acc.getActivated());
				acc.setAdmin(acc.getAdmin());
				user.saveAccountService(acc);

				m.addAttribute("tb","S???a t??i kho???n th??nh c??ng");
				
		}
		}
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize);
		Page<users> resultPage = u.findAll(pageable);
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
		m.addAttribute("userPage",resultPage);
		return "Admin/page/user";
	}
	

}
