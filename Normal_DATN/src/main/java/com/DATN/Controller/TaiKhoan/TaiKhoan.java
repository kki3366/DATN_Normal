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

import com.DATN.Entity.users;
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
		users acc = new users();
		acc.setActivated(true);
		acc.setAdmin(false);
		m.addAttribute("acc", acc);
		return "taiKhoan/SignUp";
	}
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/dk")
	public String SignUp(Model m,@Validated @ModelAttribute("acc") users acc, Errors errors,
			@RequestParam("NLpassword") String NLpass) {
		acc.setActivated(true);
		acc.setAdmin(false);
		if(errors.hasErrors()) {
			System.err.println(errors.getAllErrors());
			m.addAttribute("tb", "????ng k?? th???t b???i");
		}else {
			
			Integer kt = 0;
			Optional<users> account = users.findByUsernameService(acc.getId());
//			if(acc.getId().isEmpty()) {
//				kt++;
//				m.addAttribute("ktTonTai", "User ???? t???n t???i.");
//			}
			if (!account.isEmpty()) {
				kt++;
				m.addAttribute("ktTonTai", "username ???? t???n t???i");
			}
			if (users.findByEmailService(acc.getEmail()) != null) {
				kt++;
				m.addAttribute("ktEmail", "Email ???? t???n t???i");
			}
			if (users.findByPhoneService(acc.getPhone()) != null) {
				kt++;
				m.addAttribute("ktPhone", "S??? ??i???n tho???i ???? t???n t???i");
			}
			if (!NLpass.equals(acc.getPassword())) {
				kt++;
				m.addAttribute("ktPass", "M???t kh???u kh??ng kh???p");
			}
			if(kt==0) {
				BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
				
				acc.setPassword(pe.encode(acc.getPassword()));
				
				System.err.println("Vai Tro: "+acc.getAdmin());
				users.saveAccountService(acc);
				
				m.addAttribute("tb","????ng k?? th??nh c??ng");
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
		users user = u.findId(username);
		m.addAttribute("username", username);
		m.addAttribute("email", email);
		if(user != null && user.getEmail().equalsIgnoreCase(email)) {
			for(int i=0;i<1;i++) {
	            double random = Math.random();		             
	           random =random *1000000;   
	              randomInt =(int) random;
		}
			String nd ="M?? OTP c???a b???n l??: "+randomInt+". Vui l??ng nh???p ????? nh???n m???t kh???u m???i.";
			this.Mail(m,email,nd,"HoLy Watch");
			name = username;
			mail = email;
			return "taiKhoan/MaOTP";
		}else {
			m.addAttribute("tbforgotPassword","T??n ????ng nh???p ho???c email kh??ng ch??nh x??c");
			return "taiKhoan/FogetPass";
		}
		
	}
	@PostMapping("/XacThuc")
	public String xacThuc(Model m,@RequestParam("otp") int maXN ) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		m.addAttribute("maXN", maXN);
		boolean check = true;
		if(maXN != randomInt) {
			m.addAttribute("tbforgotPassword","M?? x??c nh???n kh??ng ????ng!");
			check = false;
			return "TaiKhoan/MaOTP";
		} 
		else{
			users acc = u.getById(name);
			for(int i=0;i<1;i++) {
	            double random = Math.random();		             
	           random =random *1000000;   
	              randomInt =(int) random;                
		}
			String nd ="M???t kh???u m???i c???a b???n l??: "+randomInt+".Vui l??ng ?????i l???i m???t kh???u.";
			this.Mail(m,mail,nd,"HoLy Watch");
			acc.setPassword(pe.encode(randomInt+""));
			users.saveAccountService(acc);
			m.addAttribute("tbforgotPassword","Vui l??ng ????ng nh???p v???i m???t kh???u m???i!");
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
		users acc =u.getById(req.getRemoteUser());
		boolean check = true;
		if(acc != null) {
			if(passMoi.isEmpty() || passMoi.isEmpty() || XNPass.isEmpty()) {
				m.addAttribute("tb","C??c d??ng ??ang tr???ng");
				check =false;
			}
				if(!pe.matches(pass,acc.getPassword())) {
					
					
					m.addAttribute("tb","M???t kh???u kh??ng ????ng");
					check =false;
				}
			
				if(pe.matches(passMoi,acc.getPassword())) {
					m.addAttribute("tb","M???t kh???u n??y ???? ?????i tr?????c ????");
					check =false;
				}
					if(!passMoi.equals(XNPass)) {
						m.addAttribute("tb","X??c nh???n m???t kh???u kh??ng ????ng");
						check =false;
					}
				
			
		}
		if(check == true) {
			acc.setPassword(pe.encode(XNPass));
			users.saveAccountService(acc);
			m.addAttribute("tb","?????i m???t kh???u th??nh c??ng");
			return "redirect:/security/logout";
		}else {
			return "taiKhoan/ChangePass";
		}
		
	}
	@GetMapping("/editProfile")
	public String editProfile(Model m) {
		m.addAttribute("username",req.getRemoteUser());
		users acc = u.getById(req.getRemoteUser());
		if(acc != null) {
			
			m.addAttribute("fullname",acc.getFullname());
			m.addAttribute("email", acc.getEmail());
			m.addAttribute("phone", acc.getPhone());
		}
		return "taiKhoan/EditProfile";
	}
	@PostMapping("/editProfile")
	public String editProfile(Model m
			,@RequestParam("fullname") String fullname,
			@RequestParam("email") String email,@RequestParam("phone") String phone) {
		
		m.addAttribute("username",req.getRemoteUser());
		users account = u.getById(req.getRemoteUser());
		if(account != null) {
			
			int kt =0;
			m.addAttribute("fullname",account.getFullname());
			m.addAttribute("email", account.getEmail());
			m.addAttribute("phone", account.getPhone());
			
					
			account.setFullname(fullname);
			account.setEmail(email);
			account.setPhone(phone);
					
					
				
					if (u.findByEmail(account.getEmail()) != null) {
						
						users x = u.findByEmailAndUser(account.getId(),account.getEmail());
						if(x == null) {
							kt++;
							m.addAttribute("ktEmail", "Email ???? t???n t???i");
						}
						
					}
////					
					if (u.findByPhone(account.getPhone()) != null) {
						users x = u.findByPhoneAndUser(account.getId(),account.getPhone());
						System.err.println(x);
						if(x == null) {
							kt++;
							System.err.println("S??? ??i???n tho???i ???? t???n t???i");
							m.addAttribute("ktPhone", "S??? ??i???n tho???i ???? t???n t???i");
						}
					}
					if(kt ==0) {
						users.saveAccountService(account);
						m.addAttribute("tb","C???p nh???t th??nh c??ng");
						return "redirect:/security/logout";
					}
					else {
						return "taiKhoan/EditProfile";
					}
					
		
		}else {
			return "taiKhoan/EditProfile";
		}
		
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
//				   svzdetnyddotvaqs
			String username = "holywatchshop@gmail.com";
			String password = "wppmztfzsqjazrfw";
			return new PasswordAuthentication(username, password);
			}
		});
		
		MimeMessage mime = new MimeMessage(session);
		
		try {
			Multipart mailmultipart = new MimeMultipart();
			
			MimeBodyPart bodytext = new MimeBodyPart();
			
			
			bodytext.setText(nd,"utf-8");
			
			
			mailmultipart.addBodyPart(bodytext);
	
			mime.setFrom(new InternetAddress("holywatchshop@gmail.com"));
			mime.setRecipients(Message.RecipientType.TO,email);
			mime.setSubject(tieude,"utf-8");
			mime.setReplyTo(mime.getFrom());
			mime.setContent(mailmultipart);
			
			Transport.send(mime);
			m.addAttribute("tbforgotPassword", "Vui l??ng ki???m Email!");
			
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.addAttribute("tbforgotPassword", "G???i M?? th???t b???i");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.addAttribute("tbforgotPassword", "G???i M?? th???t b???i");
			
		}	
	}
}
