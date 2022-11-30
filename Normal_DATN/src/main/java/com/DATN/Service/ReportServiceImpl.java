package com.DATN.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DATN.Entity.Report.ReportByInventory;
import com.DATN.Entity.Report.ReportByRevenueByCustomer;
import com.DATN.Entity.Report.ReportRevenueByCategory;
import com.DATN.Repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	ReportRepository reportRepository;
	@Override
	public List<ReportByInventory> reportByInventory() {
		return reportRepository.reportByInventory();
	}
	@Override
	public List<ReportByRevenueByCustomer> reportByRevenueCustomers() {
		return reportRepository.reportByRevenueByCustomers();
	}
	@Override
	public List<ReportRevenueByCategory> reportRevenueByCategories() {
		return reportRepository.reportRevenueByCategories();
	}
	
	
	

}
