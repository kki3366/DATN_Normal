package com.DATN.Entity.Report;

public class ReportRevenueByProduct {
	String nameProduct;
	long totalQuantity;
	long order;
	double totalSub;
	public ReportRevenueByProduct(String nameProduct, long totalQuantity, long order, double totalSub) {
		super();
		this.nameProduct = nameProduct;
		this.totalQuantity = totalQuantity;
		this.order = order;
		this.totalSub = totalSub;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public long getOrder() {
		return order;
	}
	public void setOrder(long order) {
		this.order = order;
	}
	public double getTotalSub() {
		return totalSub;
	}
	public void setTotalSub(double totalSub) {
		this.totalSub = totalSub;
	}
	
}
