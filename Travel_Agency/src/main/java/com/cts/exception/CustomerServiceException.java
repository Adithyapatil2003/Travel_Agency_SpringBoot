package com.cts.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerServiceException extends RuntimeException {

	private HttpStatus httpStatus;
	private String msg;
	
}
