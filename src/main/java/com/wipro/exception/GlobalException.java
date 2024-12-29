package com.wipro.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException ex, WebRequest web) {
        ErrorDetails ed = new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value(), web.getDescription(false));
        return new ResponseEntity<ErrorDetails>(ed, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException ex, WebRequest web) {
        ErrorDetails ed = new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.value(), web.getDescription(false));
        return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(RuntimeException ex, WebRequest web) {
        ErrorDetails ed = new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), web.getDescription(false));
        return new ResponseEntity<ErrorDetails>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
