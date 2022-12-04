package com.DATN.Entity.Report;


public class ReportByInventory{

	String nameCategory;
	Long quantiyProduct;
	Double subTotal;
	Double minProduct;
	Double maxProdouct;
	Double avgProduct;
	public ReportByInventory(String nameCategory, Long quantiyProduct, Double subTotal, Double minProduct,
			Double maxProdouct, Double avgProduct) {
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
	public Long getQuantiyProduct() {
		return quantiyProduct;
	}
	public void setQuantiyProduct(Long quantiyProduct) {
		this.quantiyProduct = quantiyProduct;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getMinProduct() {
		return minProduct;
	}
	public void setMinProduct(Double minProduct) {
		this.minProduct = minProduct;
	}
	public Double getMaxProdouct() {
		return maxProdouct;
	}
	public void setMaxProdouct(Double maxProdouct) {
		this.maxProdouct = maxProdouct;
	}
	public Double getAvgProduct() {
		return avgProduct;
	}
	public void setAvgProduct(Double avgProduct) {
		this.avgProduct = avgProduct;
	}
	
	
	
}
