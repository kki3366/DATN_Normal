package com.DATN.Entity.Report;

public class ReportByPopularProduct {
	
	String nameCategory;
	String nameProduct;
	long times;
	public ReportByPopularProduct(String nameCategory, String nameProduct, long times) {
		super();
		this.nameCategory = nameCategory;
		this.nameProduct = nameProduct;
		this.times = times;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
	
}
