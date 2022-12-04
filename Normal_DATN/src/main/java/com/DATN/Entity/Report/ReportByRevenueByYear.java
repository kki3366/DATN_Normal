package com.DATN.Entity.Report;

import java.util.Date;

public class ReportByRevenueByYear {
	int year;
	long quantity;
	double totalSub;
	double minPrice;
	double maxPrice;
	double avgPrice;
	public ReportByRevenueByYear(int year, long quantity, double totalSub, double minPrice, double maxPrice,
			double avgPrice) {
		super();
		this.year = year;
		this.quantity = quantity;
		this.totalSub = totalSub;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.avgPrice = avgPrice;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
