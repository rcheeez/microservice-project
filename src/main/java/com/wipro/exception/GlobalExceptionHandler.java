package com.wipro.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails> productNotFoundException(ProductNotFoundException pex, WebRequest web){
		ErrorDetails ed = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND.value(), pex.getMessage(), web.getDescription(false));
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
	}
	
	@ExceptionHandler(CouponNotFoundException.class)
	public ResponseEntity<ErrorDetails> couponNotFoundException(CouponNotFoundException cex, WebRequest web){
		ErrorDetails ed = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND.value(), cex.getMessage(), web.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> globalException(RuntimeException ex, WebRequest web){
		ErrorDetails ed = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), web.getDescription(false));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ed);
	}
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
		return "Accept: "+ MediaType.APPLICATION_JSON_VALUE;
	}
}
