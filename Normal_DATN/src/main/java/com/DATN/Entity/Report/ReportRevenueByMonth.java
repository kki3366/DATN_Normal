package com.DATN.Entity.Report;

public class ReportRevenueByMonth {
	int years;
	int month;
	long quantity;
	double totalSub;
	double minPrice;
	double maxPrice;
	double avgPrice;
	public ReportRevenueByMonth(int years, int month, long quantity, double totalSub, double minPrice, double maxPrice,
			double avgPrice) {
		super();
		this.years = years;
		this.month = month;
		this.quantity = quantity;
		this.totalSub = totalSub;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.avgPrice = avgPrice;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getTotalSub() {
		return totalSub;
	}
	public void setTotalSub(double totalSub) {
		this.totalSub = totalSub;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	
	
	
}
