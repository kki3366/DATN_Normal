package com.DATN.Controller.TaiKhoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.Users;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.UserServiceImpl;






@Controller
public class TaiKhoan {

	@Autowired
	UserServiceImpl users;
	@Autowired
	UserRepository u;
	@Autowired
	HttpServletRequest req;
	static String mail;
    static int  randomInt;
    static int  passmoi;
    static String name;
	@RequestMapping("/login")
	public String index() {
		return "taiKhoan/login";
	}
	@GetMapping("/dk")
	public String SignUp(Model m) {
		Users acc = new Users();
		m.addAttribute("acc", acc);
		return "taiKhoan/SignUp";
	}
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/dk")
	public String SignUp(Model m,@Validated @ModelAttribute("acc") Users acc, Errors errors,
			@RequestParam("NLpassword") String NLpass) {
		if(errors.hasErrors()) {
			m.addAttribute("tb", "Vui lòng sửa các lỗi sau:");
		}else {
			Integer kt = 0;
			Optional<Users> account = users.findByUsernameService(acc.getId());
			System.err.println(account);
			if (!account.isEmpty()) {
				kt++;
				m.addAttribute("ktTonTai", "User đã tồn tại.");
			}
			if (users.findByEmailService(acc.getEmail()) != null) {
				kt++;
				m.addAttribute("ktEmail", "Email đã tồn tại");
			}
			if (!NLpass.equals(acc.getPassword())) {
				kt++;
				m.addAttribute("ktPass", "Mật khẩu không khớp");
			}
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(acc.getPassword()));
				acc.setActivated(true);
				acc.setAdmin(false);
				users.saveAccountService(acc);
				
				m.addAttribute("tb","Đăng ký thành công");
		}
		}
		return "taiKhoan/SignUp";
	}
	@GetMapping("/fogetPass")
	public String fogetPass() {
		return "taiKhoan/FogetPass";
	}
	@PostMapping("/fogetPass")
	public String fogetPass(Model m,@RequestParam("username") String username, @RequestParam("email") String email) {
		Users user = u.getById(username);
		m.addAttribute("username", username);
		m.addAttribute("email", email);
		if(user != null && user.getEmail().equalsIgnoreCase(email)) {
			for(int i=0;i<1;i++) {
	            double random = Math.random();		             
	           random =random *1000000;   
	              randomInt =(int) random;
		}
			String nd ="Nhập mã "+randomInt+" để xác nhận đổi mật khẩu";
			this.Mail(m,email,nd,"Mã xác nhận");
			name = username;
			mail = email;
			return "taiKhoan/MaOTP";
		}else {
			m.addAttribute("tbforgotPassword","Tên đăng nhập hoặc email không chính xác");
			return "taiKhoan/FogetPass";
		}
		
	}
	@PostMapping("/XacThuc")
	public String xacThuc(Model m,@RequestParam("otp") int maXN ) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		m.addAttribute("maXN", maXN);
		if(maXN != randomInt) {
			m.addAttribute("tbforgotPassword","Mã xác nhận không đúng!");
			return "TaiKhoan/MaOTP";
		}else {
			Users acc = u.getById(name);
			for(int i=0;i<1;i++) {
	            double random = Math.random();		             
	           random =random *1000000;   
	              randomInt =(int) random;                
		}
			String nd ="Mật khẩu mới của bạn là:"+randomInt;
			this.Mail(m,mail,nd,"Mật khẩu mới");
			acc.setPassword(pe.encode(randomInt+""));
			users.saveAccountService(acc);
			m.addAttribute("tbforgotPassword","Vui lòng đăng nhập với mật khẩu mới!");
			return "TaiKhoan/login";
		}
		
	}
	@GetMapping("/changePass")
	public String changePass(Model m) {
		m.addAttribute("username",req.getRemoteUser());
		return "taiKhoan/ChangePass";
	}
	@PostMapping("/changePass")
	public String changePass(Model m,@RequestParam("password") String pass,@RequestParam("passwordMoi") String passMoi,
			@RequestParam("passwordXN") String XNPass) {
		m.addAttribute("username",req.getRemoteUser());
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		Users acc =u.getById(req.getRemoteUser());
		if(acc != null) {
			if(passMoi.isEmpty() || passMoi.isEmpty() || XNPass.isEmpty()) {
				m.addAttribute("tb","Các dòng đang trống");
			}else {
				if(pe.matches(pass,acc.getPassword())) {
					if(pe.matches(passMoi,acc.getPassword())) {
						m.addAttribute("tb","Mật khẩu này đã đổi trước đó");
						
					}else {
						if(passMoi.equals(XNPass)) {
							acc.setPassword(pe.encode(passMoi));
							users.saveAccountService(acc);
							m.addAttribute("tb","Đổi mật khẩu thành công");
						}else {
							m.addAttribute("tb","Xác nhận mật khẩu không đúng");
						}
					}
					
				
				}else {
					m.addAttribute("tb","Mật khẩu cũ không đúng");
				}
			}
			
			
		}else {
			m.addAttribute("tb","Username không tồn tại");
		
		}
		return "taiKhoan/ChangePass";
	}
	@GetMapping("/editProfile")
	public String editProfile(Model m) {
		m.addAttribute("username",req.getRemoteUser());
		Users acc = u.getById(req.getRemoteUser());
		if(acc != null) {
			m.addAttribute("pass",acc.getPassword());
			m.addAttribute("fullname",acc.getFullname());
			m.addAttribute("email", acc.getEmail());
		}
		return "taiKhoan/EditProfile";
	}
	@PostMapping("/editProfile")
	public String editProfile(Model m,@RequestParam("password") String pass,@RequestParam("fullname") String fullname,
			@RequestParam("email") String email) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		m.addAttribute("username",req.getRemoteUser());
		Users acc = u.getById(req.getRemoteUser());
		if(acc != null) {
			
			if(!pass.isEmpty() && !fullname.isEmpty() && !email.isEmpty()) {
				acc.setPassword(pe.encode(pass));
				acc.setFullname(fullname);
				acc.setEmail(email);
				users.saveAccountService(acc);
				m.addAttribute("tb","Cập nhật thành công");
				m.addAttribute("pass",acc.getPassword());
				m.addAttribute("fullname",acc.getFullname());
				m.addAttribute("email",acc.getEmail());
			}else {
				m.addAttribute("tb","Cập nhật thất bại");
			}
		}
		return "taiKhoan/EditProfile";
	}
	public void Mail(Model m,String email,String nd,String tieude) {
		Properties props = new Properties(); 
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true"); 
		props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
		props.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
		props.setProperty("mail.smtp.ssl.protocols","TLSv1.2");
		props.setProperty("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() {
		
			String username = "trungttpc01815@fpt.edu.vn";
			String password = "zloucbkhuqdcuwfd";
			return new PasswordAuthentication(username, password);
			}
		});
		
		MimeMessage mime = new MimeMessage(session);
		
		try {
			Multipart mailmultipart = new MimeMultipart();
			
			MimeBodyPart bodytext = new MimeBodyPart();
			
			
			bodytext.setText(nd,"utf-8");
			
			
			mailmultipart.addBodyPart(bodytext);
	
			mime.setFrom(new InternetAddress("trungttpc01815@fpt.edu.vn"));
			mime.setRecipients(Message.RecipientType.TO,email);
			mime.setSubject(tieude,"utf-8");
			mime.setReplyTo(mime.getFrom());
			mime.setContent(mailmultipart);
			
			Transport.send(mime);
			m.addAttribute("tbforgotPassword", "Vui lòng kiểm Email!");
			
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.addAttribute("tbforgotPassword", "Gửi Mã thất bại");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.addAttribute("tbforgotPassword", "Gửi Mã thất bại");
			
		}	
	}
}
