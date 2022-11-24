package com.DATN.Controller.NguoiDung;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Contact;
import com.DATN.Entity.users;
import com.DATN.Repository.UserRepository;
import com.DATN.Repository.contactRepository;
import com.DATN.Service.UserServiceImpl;

@Controller
public class ContactUs {

	@Autowired
	HttpServletRequest req;
	@Autowired
	UserRepository users;
	@Autowired
	contactRepository contactRep;
	@GetMapping("/ContactUs")
	public String contactUs(Model m) {
		m.addAttribute("contact",new Contact());
		return "nguoiDung/contactUs";
	}
	
	@PostMapping("/ContactUs")
	public String contactUs(Model m,@Validated @ModelAttribute("contact") Contact contact,Errors errors) {
		if(errors.hasErrors()) {
//			m.addAttribute("tb", "Vui lòng nhập đủ các trường");
		}else {
			
//			if(check == true) {
				contact.setStatus("Chờ phản hồi");
				users acc = users.getById(req.getRemoteUser());
				System.err.println(acc.getId());
				contact.setUser(acc);
				contactRep.save(contact);
				m.addAttribute("tb", "Đã gửi");
//			}
		}
		return "nguoiDung/contactUs";
	}
}
