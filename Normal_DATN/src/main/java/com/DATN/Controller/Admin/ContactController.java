package com.DATN.Controller.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Contact;
import com.DATN.Entity.users;
import com.DATN.Repository.contactRepository;

@Controller
public class ContactController {

	@Autowired
	contactRepository contact;
	@RequestMapping("/admin/contact")
	public String contact(Model m,@RequestParam("p") Optional<Integer> p) {
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<Contact> pageList = contact.findAll(page);
		m.addAttribute("page",pageList);
		m.addAttribute("contact",new Contact());
		m.addAttribute("edit",false);
		m.addAttribute("send",false);
		return "Admin/page/ContactAd";
	}
	
	@RequestMapping("/admin/contact/edit")
	public String edit(Model m,@RequestParam("id") int idContact,@RequestParam("p") Optional<Integer> p) {
		Contact Contact = contact.getById(idContact);
		
		m.addAttribute("contact",Contact);
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<Contact> pageList = contact.findAll(page);
		m.addAttribute("page",pageList);
		m.addAttribute("edit",false);
		m.addAttribute("send",true);
		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/writefeedback")
	public String phanhoi(Model m,@RequestParam("p") Optional<Integer> p) {
		
		m.addAttribute("contact",new Contact());
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<Contact> pageList = contact.findAll(page);
		m.addAttribute("page",pageList);

		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/feedback")
	public String phanhoi(Model m,@RequestParam("subject") String subject,@RequestParam("content") String content,@RequestParam("p") Optional<Integer> p) {
		Boolean check = true;
		if(subject.isEmpty()) {
			m.addAttribute("subject","Bạn chưa nhập tiêu đề");
			check = false;
		}
		if(content.isEmpty()) {
			m.addAttribute("content","Bạn chưa nhập nội dung");
			check = false;
		}
		if(check == true) {
			
		}
		m.addAttribute("contact",new Contact());
		Pageable page = PageRequest.of(p.orElse(0), 5);
		Page<Contact> pageList = contact.findAll(page);
		m.addAttribute("page",pageList);

		return "Admin/page/ContactAd";
	}
}
