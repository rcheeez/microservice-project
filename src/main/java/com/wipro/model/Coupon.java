package com.wipro.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long couponId;
	private String couponCode;
	private LocalDate expirationDate;
	private int discountPercentage;

	public Coupon() {
		super();
	}

	public Coupon(long couponId, String couponCode, LocalDate expirationDate, int discountPercentage) {
		super();
		this.couponId = couponId;
		this.couponCode = couponCode;
		this.expirationDate = expirationDate;
		this.discountPercentage = discountPercentage;
	}

	public boolean isValid() {
		LocalDate currentDate = LocalDate.now();
		return expirationDate == null || !expirationDate.isBefore(currentDate);
	}
	

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
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
		return "Coupon [couponId=" + couponId + ", couponCode=" + couponCode
				+ ", expirationDate=" + expirationDate + ", discountPercentage=" + discountPercentage + "]";
	}
}
