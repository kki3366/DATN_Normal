package com.DATN.Entity.Report;


public class ReportByInventory{

	String nameCategory;
	long quantiyProduct;
	double subTotal;
	double minProduct;
	double maxProdouct;
	double avgProduct;
	public ReportByInventory(String nameCategory, long quantiyProduct, double subTotal, double minProduct,
			double maxProdouct, double avgProduct) {
		super();
		this.nameCategory = nameCategory;
		this.quantiyProduct = quantiyProduct;
		this.subTotal = subTotal;
		this.minProduct = minProduct;
		this.maxProdouct = maxProdouct;
		this.avgProduct = avgProduct;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public long getQuantiyProduct() {
		return quantiyProduct;
	}
	public void setQuantiyProduct(long quantiyProduct) {
		this.quantiyProduct = quantiyProduct;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getMinProduct() {
		return minProduct;
	}
	public void setMinProduct(double minProduct) {
		this.minProduct = minProduct;
	}
	public double getMaxProdouct() {
		return maxProdouct;
	}
	public void setMaxProdouct(double maxProdouct) {
		this.maxProdouct = maxProdouct;
	}
	public double getAvgProduct() {
		return avgProduct;
	}
	public void setAvgProduct(double avgProduct) {
		this.avgProduct = avgProduct;
	}
	
	
	
}
