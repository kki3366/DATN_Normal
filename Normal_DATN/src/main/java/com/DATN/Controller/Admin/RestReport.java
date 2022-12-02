package com.DATN.Controller.Admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DATN.Entity.Report.ReportByInventory;
import com.DATN.Entity.Report.ReportByPopularProduct;
import com.DATN.Entity.Report.ReportByRevenueByCustomer;
import com.DATN.Entity.Report.ReportRevenueByCategory;
import com.DATN.Entity.Report.ReportRevenueByProduct;
import com.DATN.Service.ReportService;

@RestController
@RequestMapping("api")
public class RestReport {
	
	@Autowired
	ReportService reportService;
	
	
	@GetMapping("/report/inventory")
	public ResponseEntity<List<ReportByInventory>> byInventory() {
		return new ResponseEntity<List<ReportByInventory>>(reportService.reportByInventory().stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/report/revenueByCustomer")
	public ResponseEntity<List<ReportByRevenueByCustomer>> byCustomer(){
		return new ResponseEntity<List<ReportByRevenueByCustomer>>(reportService.reportByRevenueCustomers().stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/report/revenueByCategory")
	public ResponseEntity<List<ReportRevenueByCategory>> byCategory(){
		return new ResponseEntity<List<ReportRevenueByCategory>>(reportService.reportRevenueByCategories().stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/report/popularProduct")
	public ResponseEntity<List<ReportByPopularProduct>> reportPopular(){
		return new ResponseEntity<List<ReportByPopularProduct>>(reportService.reportByPopularProducts().stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/report/reportRevenueByProduct")
	public ResponseEntity<List<ReportRevenueByProduct>> reportProduct(){
		return new ResponseEntity<List<ReportRevenueByProduct>>(reportService.reportRevenueByProducts().stream().collect(Collectors.toList()), HttpStatus.OK);
	}
	
}
