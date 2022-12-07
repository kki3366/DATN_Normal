package com.DATN.Entity.Report;

public class ReportByRevenueByCustomer {
	String userId;
	Long quantity;
	Double subTotal;
	Double minPriceProduct;
	Double maxPriceProduct;
	Double avgPriceProduct;
	public ReportByRevenueByCustomer(String userId, Long quantity, Double subTotal, Double minPriceProduct,
			Double maxPriceProduct, Double avgPriceProduct) {
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
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getMinPriceProduct() {
		return minPriceProduct;
	}
	public void setMinPriceProduct(Double minPriceProduct) {
		this.minPriceProduct = minPriceProduct;
	}
	public Double getMaxPriceProduct() {
		return maxPriceProduct;
	}
	public void setMaxPriceProduct(Double maxPriceProduct) {
		this.maxPriceProduct = maxPriceProduct;
	}
	public Double getAvgPriceProduct() {
		return avgPriceProduct;
	}
	public void setAvgPriceProduct(Double avgPriceProduct) {
		this.avgPriceProduct = avgPriceProduct;
	}
	
	
}
