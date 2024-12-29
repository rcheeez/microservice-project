package com.wipro.model;

import java.time.LocalDate;

public class CouponDetails {

	private int discountPercentage;
	private LocalDate expirationDate;
	
	public CouponDetails(int discountPercentage, LocalDate expirationDate) {
		super();
		this.discountPercentage = discountPercentage;
		this.expirationDate = expirationDate;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "CouponDetails [discountPercentage=" + discountPercentage + ", expirationDate=" + expirationDate + "]";
	}	
}
