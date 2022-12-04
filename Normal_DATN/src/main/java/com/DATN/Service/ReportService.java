package com.DATN.Service;

import java.util.List;

import com.DATN.Entity.Report.ReportByInventory;
import com.DATN.Entity.Report.ReportByPopularProduct;
import com.DATN.Entity.Report.ReportByRevenueByCustomer;
import com.DATN.Entity.Report.ReportByRevenueByYear;
import com.DATN.Entity.Report.ReportRevenueByCategory;
import com.DATN.Entity.Report.ReportRevenueByMonth;
import com.DATN.Entity.Report.ReportRevenueByProduct;
import com.DATN.Entity.Report.ReportRevenueByQuarter;

public interface ReportService {

	 List<ReportByInventory> reportByInventory();
	 
	 List<ReportByRevenueByCustomer> reportByRevenueCustomers();
	 
	 List<ReportRevenueByCategory> reportRevenueByCategories();
	 
	 List<ReportByPopularProduct> reportByPopularProducts();
	 
	 List<ReportRevenueByProduct> reportRevenueByProducts();
	 
	 List<ReportByRevenueByYear> reportByRevenueByYears();
	 
	 List<ReportRevenueByQuarter> reportRevenueByQuarters();
	 
	 List<ReportRevenueByMonth> reportRevenueByMonths();
}
