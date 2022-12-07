package com.DATN.Entity.Report;

public class ReportRevenueByQuarter {
	int years;
	int  quarter;
	Long quantity;
	Double totalSub;
	Double minPrice;
	Double maxPrice;
	Double avgPrice;
	public ReportRevenueByQuarter(int years, int quarter, Long quantity, Double totalSub, Double minPrice,
			Double maxPrice, Double avgPrice) {
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
