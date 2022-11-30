package com.DATN.Service;

import java.util.List;

import com.DATN.Entity.Report.ReportByInventory;
import com.DATN.Entity.Report.ReportByRevenueByCustomer;

public interface ReportService {

	 List<ReportByInventory> reportByInventory();
	 
	 List<ReportByRevenueByCustomer> reportByRevenueCustomers();
}
