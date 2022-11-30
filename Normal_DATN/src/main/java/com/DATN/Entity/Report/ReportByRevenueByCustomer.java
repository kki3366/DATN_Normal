package com.DATN.Entity.Report;

public class ReportByRevenueByCustomer {
	String userId;
	long quantity;
	double subTotal;
	double minPriceProduct;
	double maxPriceProduct;
	double avgPriceProduct;
	public ReportByRevenueByCustomer(String userId, long quantity, double subTotal, double minPriceProduct,
			double maxPriceProduct, double avgPriceProduct) {
		super();
		this.userId = userId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.minPriceProduct = minPriceProduct;
		this.maxPriceProduct = maxPriceProduct;
		this.avgPriceProduct = avgPriceProduct;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getMinPriceProduct() {
		return minPriceProduct;
	}
	public void setMinPriceProduct(double minPriceProduct) {
		this.minPriceProduct = minPriceProduct;
	}
	public double getMaxPriceProduct() {
		return maxPriceProduct;
	}
	public void setMaxPriceProduct(double maxPriceProduct) {
		this.maxPriceProduct = maxPriceProduct;
	}
	public double getAvgPriceProduct() {
		return avgPriceProduct;
	}
	public void setAvgPriceProduct(double avgPriceProduct) {
		this.avgPriceProduct = avgPriceProduct;
	}
	
	
}
