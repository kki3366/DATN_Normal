package com.DATN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

	@GetMapping("admin")
	public String rootAdminPage() {
		return "Admin/components/header";
	}
	
	
	@GetMapping ("category")
	public String categoryPage() {
		return "Admin/category";
	}
}
