package com.wipro.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public Optional<Coupon> findByCouponCode(String code);
}
