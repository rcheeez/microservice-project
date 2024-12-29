package com.wipro.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CouponNotFoundException.class)
	public ResponseEntity<ErrorDetails> couponNotFoundException(CouponNotFoundException cex, WebRequest web){
		ErrorDetails ed = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND.value(), cex.getMessage(), web.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> GlobalException(RuntimeException ex, WebRequest web) {
		ErrorDetails ed = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), web.getDescription(false));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ed);
	}
	
}
