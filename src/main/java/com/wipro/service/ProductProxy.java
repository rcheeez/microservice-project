package com.wipro.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.exception.CouponNotFoundException;
import com.wipro.model.Coupon;

@FeignClient(name = "coupon-service", url = "${pivotal.couponservice.url}")
public interface ProductProxy {

	@GetMapping("/api/coupon/validate/{code}")
	public Optional<Coupon> getByCouponCode(@PathVariable String code) throws CouponNotFoundException;
	
}