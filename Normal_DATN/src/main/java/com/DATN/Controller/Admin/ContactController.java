package com.DATN.Controller.Admin;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.SessionService;
import com.DATN.Entity.Contact;
import com.DATN.Entity.users;
import com.DATN.Repository.contactRepository;

@Controller
public class ContactController {

	@Autowired
	contactRepository contact;
	@Autowired
	HttpServletRequest req;
	@Autowired
	SessionService session;	
	// biến lưu id contact de tim email
	int id;
	@RequestMapping("/admin/contact")
	public String contact(Model m,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		id =0;
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize,Sort.by(Direction.DESC,"date"));
		Page<Contact> resultPage = contact.findAll(pageable);
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
		m.addAttribute("contactPage",resultPage);
	
			m.addAttribute("contact",new Contact());
			m.addAttribute("edit",false);
			m.addAttribute("send",false);
		
		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/find")
	public String find(Model m,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {
		m.addAttribute("contact",new Contact());
		
		String kwords = kw.orElse(session.getAttribute("keywords"));
		session.setAttribute("keywords", kwords);
		m.addAttribute("keywords", kwords);
		
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize,Sort.by(Direction.DESC,"date"));
		Page<Contact> resultPage = contact.findByKeywords("%"+kwords+"%", pageable);
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
		m.addAttribute("contactPage",resultPage);
//		return "redirect:/admin/user";
		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/edit")
	public String edit(Model m,@RequestParam("id") int idContact,@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {
		Contact Contact = contact.getById(idContact);
		id = idContact;
		m.addAttribute("contact",Contact);
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize,Sort.by(Direction.DESC,"date"));
		Page<Contact> resultPage = contact.findAll(pageable);
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
		m.addAttribute("contactPage",resultPage);
		
		m.addAttribute("edit",false);
		if(Contact.getStatus() == false) {
			m.addAttribute("send",true);
		}else {
			m.addAttribute("send",false);
		}
		
		
		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/delete")
	public String delete(Model m,@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {
		if(id == 0) {
			m.addAttribute("tb","Bạn chưa chọn nội dung để xóa");
			m.addAttribute("contact",new Contact());
		}else {
			Contact Contact = contact.getById(id);
			contact.delete(Contact);
			m.addAttribute("contact",Contact);
			m.addAttribute("tb","Xóa thành công");
		}
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize,Sort.by(Direction.DESC,"date"));
		Page<Contact> resultPage = contact.findAll(pageable);
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
		m.addAttribute("contactPage",resultPage);
		
		m.addAttribute("edit",false);
		
		
		
		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/read")
	public String phanhoi(Model m,@RequestParam("p") Optional<Integer> p,@RequestParam("s") Optional<Integer> s) {
		
		m.addAttribute("contact",new Contact());
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize,Sort.by(Direction.DESC,"date"));
		Page<Contact> resultPage = contact.findAll(pageable);
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
		m.addAttribute("contactPage",resultPage);

		return "Admin/page/ContactAd";
	}
	@RequestMapping("/admin/contact/feedback")
	public String phanhoi(Model m,@RequestParam("subject") String subject,@RequestParam("content") String content,@RequestParam("p") Optional<Integer> p,
			@RequestParam("s") Optional<Integer> s) {
		Boolean check = true;
		
		Contact Contact = contact.getById(id);
		if(subject.isEmpty()) {
			m.addAttribute("subject","Bạn chưa nhập tiêu đề");
			check = false;
		}
		if(content.isEmpty()) {
			m.addAttribute("content","Bạn chưa nhập nội dung");
			check = false;
		}
		if(check == true) {
			Properties props = new Properties(); 
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.starttls.enable", "true"); 
			props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
			props.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
			props.setProperty("mail.smtp.ssl.protocols","TLSv1.2");
			props.setProperty("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props, new Authenticator() { 
				protected PasswordAuthentication getPasswordAuthentication() {
			
				String username = "holywatchct@gmail.com";
				String password = "kgmtmuieyixjkwbq";
				return new PasswordAuthentication(username, password);
				}
			});
			
			MimeMessage mime = new MimeMessage(session);
			
			try {
				Multipart mailmultipart = new MimeMultipart();
				
				MimeBodyPart bodytext = new MimeBodyPart();
				
				
				bodytext.setText(content,"utf-8");
				
				
				mailmultipart.addBodyPart(bodytext);
		
				mime.setFrom(new InternetAddress("trungttpc01815@fpt.edu.vn"));
				mime.setRecipients(Message.RecipientType.TO,Contact.getEmail());
				mime.setSubject(subject,"utf-8");
				mime.setReplyTo(mime.getFrom());
				mime.setContent(mailmultipart);
				
				Transport.send(mime);
				
				Contact.setStatus(true);
				contact.save(Contact);
				m.addAttribute("tbContact", "Đã gửi phản hồi!");
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				m.addAttribute("tbContact", "Gửi Phản hồi thất bại");
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				m.addAttribute("tbContact", "Gửi Phản hồi  thất bại");
				
			}	
		}
		
		m.addAttribute("contact",new Contact());
		int currentPage = p.orElse(0);
		int pagesize = s.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pagesize,Sort.by(Direction.DESC,"date"));
		Page<Contact> resultPage = contact.findAll(pageable);
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
		m.addAttribute("contactPage",resultPage);

		return "Admin/page/ContactAd";
	}
}
