package com.wipro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}