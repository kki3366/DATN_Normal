package com.DATN.Entity.Report;

import java.util.Date;

public class ReportByRevenueByYear {
	int year;
	Long quantity;
	Double totalSub;
	Double minPrice;
	Double maxPrice;
	Double avgPrice;
	public ReportByRevenueByYear(int year, Long quantity, Double totalSub, Double minPrice, Double maxPrice,
			Double avgPrice) {
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
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getTotalSub() {
		return totalSub;
	}
	public void setTotalSub(Double totalSub) {
		this.totalSub = totalSub;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
	
	
	
}
