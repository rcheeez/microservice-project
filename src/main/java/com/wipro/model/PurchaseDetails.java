package com.wipro.model;

public class PurchaseDetails {

	private String productName;
	private double originalPrice;
	private double discountedPrice;
	
	public PurchaseDetails(String productName, double originalPrice, double discountedPrice) {
		super();
		this.productName = productName;
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
	}
	
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "PurchaseDetails [productName=" + productName + ", originalPrice=" + originalPrice + ", discountedPrice="
				+ discountedPrice + "]";
	}
}
