package com.DATN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

	
	@GetMapping("test")
	public String t(){
		return "Admin/test/a";
	}
	
	@GetMapping("testb")
	public String b() {
		return "Admin/category";
	}
//	
}
