package com.DATN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("admin/product")
	public String p() {
		return "Admin/page/product";
	}
	@GetMapping("/admin/productt")
	public String pr() {
		return "Admin/page/test";
	}
}
