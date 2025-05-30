package com.cts.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.dtos.ErrorResponse;
import com.cts.exception.CustomerNotFoundException;
import com.cts.exception.TourNotFoundException;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
		ErrorResponse r = new ErrorResponse();
		r.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(r, HttpStatusCode.valueOf(404));
	}

	@ExceptionHandler(TourNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTourNotFoundException(TourNotFoundException ex) {
		ErrorResponse r = new ErrorResponse();
		r.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(r, HttpStatusCode.valueOf(404));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		StringBuilder s = new StringBuilder();
		for (FieldError f : ex.getBindingResult().getFieldErrors()) {
			s.append(f.getField() + ":" + f.getDefaultMessage() + "-----------");
		}
		ErrorResponse r = new ErrorResponse();
		r.setMessage(s.toString());
		return ResponseEntity.badRequest().body(r);
	}
}
