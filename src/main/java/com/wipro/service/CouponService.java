package com.wipro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.exception.CouponNotFoundException;
import com.wipro.model.Coupon;
import com.wipro.model.CouponDetails;
import com.wipro.repo.CouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository repository;
	
	public List<Coupon> listAllCoupons() throws CouponNotFoundException{
		List<Coupon> coupons = repository.findAll();
		if (coupons.isEmpty()) {
			throw new CouponNotFoundException("No Coupons Found!");
		}
		return coupons;
	}
	
	public Coupon generateCoupon(Coupon coupon) {
		repository.save(coupon);
		return coupon;
	}
	
	public CouponDetails validateCoupon(String code) throws CouponNotFoundException {
		Optional<Coupon> coup = repository.findByCouponCode(code);
		if (coup.isEmpty()) {
			throw new CouponNotFoundException("Coupon Not Found");
		}
		Coupon coupon = coup.get();
		
		CouponDetails couponDetails = new CouponDetails(coupon.getDiscountPercentage(), coupon.getExpirationDate());
		
		if (!coupon.isValid()) {
			throw new CouponNotFoundException("Coupon has been expired!");
		}
		
		return couponDetails;
	}
	
	public Coupon getCouponByCode(String code) throws CouponNotFoundException {
		Optional<Coupon> coupon = repository.findByCouponCode(code);
		if (coupon.isEmpty()) {
			throw new CouponNotFoundException("No Coupon Found with this Code: "+ code);
		}
		return coupon.get();
	}
	
	public Map<String, Boolean> deleteCoupon(long id) throws CouponNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		Optional<Coupon> coupon = repository.findById(id);
		if (coupon.isEmpty()) {
			throw new CouponNotFoundException("No Coupon Found with thus id: "+ id);
		}
		repository.delete(coupon.get());
		response.put("Coupon Deleted", true);
		return response;
	}
}
