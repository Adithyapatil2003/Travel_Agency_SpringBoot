package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dtos.AuthResponse;
import com.cts.dtos.LoginDto;
import com.cts.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
		var r = authService.login(loginDto);
		AuthResponse response = new AuthResponse();
		response.setJwtToken(r);
		response.setType("Bearer");
		return ResponseEntity.ok(response);
	}
}
