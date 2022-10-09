package com.DATN.Controller.TaiKhoan;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DATN.Service.UserServiceImpl;





@Controller
public class SecurityController {
     @Autowired
     UserServiceImpl account;
	@RequestMapping("/security/login/form")
	public String loginForm(Model m) {
		m.addAttribute("tb","Vui lòng đăng nhập!!");
		return "taiKhoan/login";
	}
	@RequestMapping("/security/success")
	public String loginSucess(Model m) {
		m.addAttribute("tb","Đăng nhập thành công");
		return "taiKhoan/login";
	}
	@RequestMapping("/loginSuccess")
	public String oauthSucess(OAuth2AuthenticationToken oauth2) {
		account.loginfromOAuth2(oauth2);
		return "forward:/index";
	}
	@RequestMapping("/security/error")
	public String loginError(Model m) {
		m.addAttribute("tb","Sai thông tin đăng nhập!!");
		return "taiKhoan/login";
	}
	@RequestMapping("/security/unauthoried")
	public String Unauthoried(Model m) {
		m.addAttribute("tb","Không có quyền truy cập!!");
		return "taiKhoan/login";
	}
	@RequestMapping("/security/logout/success")
	public String LogoutSuccess(Model m) {
		m.addAttribute("tb","Bạn đã đăng xuất!!");
		return "taiKhoan/login";
	}
	
}
