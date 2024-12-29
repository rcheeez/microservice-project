package com.wipro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.exception.CouponNotFoundException;
import com.wipro.model.Coupon;
import com.wipro.model.CouponDetails;
import com.wipro.service.CouponService;

@RefreshScope
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

	@Autowired
	private CouponService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Coupon>> showAllCoupons() throws CouponNotFoundException{
		List<Coupon> coupons =  service.listAllCoupons();
		return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/get/{code}")
	public ResponseEntity<Coupon> showCouponByCode(@PathVariable String code) throws CouponNotFoundException {
		Coupon coupon =  service.getCouponByCode(code);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	
	@GetMapping("/validate/{code}")
	public ResponseEntity<CouponDetails> validateCouponByCode(@PathVariable String code) throws CouponNotFoundException{
		CouponDetails couponDetails = service.validateCoupon(code);
		return new ResponseEntity<CouponDetails>(couponDetails, HttpStatus.OK);
	}
	
	@PostMapping("/generate")
	public ResponseEntity<Coupon> generateCoupon(@RequestBody Coupon coupon) {
		Coupon coup = service.generateCoupon(coupon);
		return new ResponseEntity<Coupon>(coup, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCoupon(@PathVariable long id) throws CouponNotFoundException {
		Map<String, Boolean> res = service.deleteCoupon(id);
		return new ResponseEntity<Map<String,Boolean>>(res, HttpStatus.OK);
	}
}
