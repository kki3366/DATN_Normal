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
}
