package com.DATN.Entity.Report;

public class ReportRevenueByProduct {
	String nameProduct;
	Long totalQuantity;
	Long order;
	Double totalSub;
	public ReportRevenueByProduct(String nameProduct, Long totalQuantity, Long order, Double totalSub) {
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
	public Long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public Double getTotalSub() {
		return totalSub;
	}
	public void setTotalSub(Double totalSub) {
		this.totalSub = totalSub;
	}
	
}
