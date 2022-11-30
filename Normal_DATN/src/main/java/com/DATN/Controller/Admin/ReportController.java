package com.DATN.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {
	
	@GetMapping ("admin/report/inventory")
	public String reportPage() {
		return "Admin/page/ReportByInventory";
	}
	
	@GetMapping ("admin/report/revenueByCustomer")
	public String reportCustomer() {
		return "Admin/page/ReportRevenueByCustomer";
	}
	
	@GetMapping ("admin/report/commentPerDay")
	public String reportComment() {
		return "Admin/page/ReportCommentPerDay";
	}
	
}
