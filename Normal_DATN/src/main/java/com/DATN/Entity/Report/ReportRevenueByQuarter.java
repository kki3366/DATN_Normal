package com.DATN.Entity.Report;

public class ReportRevenueByQuarter {
	int years;
	int  quarter;
	long quantity;
	double totalSub;
	double minPrice;
	double maxPrice;
	double avgPrice;
	public ReportRevenueByQuarter(int years, int quarter, long quantity, double totalSub, double minPrice,
			double maxPrice, double avgPrice) {
		super();
		this.years = years;
		this.quarter = quarter;
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
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
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
