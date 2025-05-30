package com.cts.security;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		response.setContentType("application/json");
		response.getWriter().write("{\"error\":" + authException.getMessage() + "}");

	}

}

