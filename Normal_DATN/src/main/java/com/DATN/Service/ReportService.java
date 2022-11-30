package com.DATN.Service;

import java.util.List;

import com.DATN.Entity.Report.ReportByInventory;
import com.DATN.Entity.Report.ReportByRevenueByCustomer;
import com.DATN.Entity.Report.ReportRevenueByCategory;

public interface ReportService {

	 List<ReportByInventory> reportByInventory();
	 
	 List<ReportByRevenueByCustomer> reportByRevenueCustomers();
	 
	 List<ReportRevenueByCategory> reportRevenueByCategories();
}
