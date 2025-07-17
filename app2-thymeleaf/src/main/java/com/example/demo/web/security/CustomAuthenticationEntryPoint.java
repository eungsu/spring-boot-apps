package com.example.demo.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.web.response.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		
		if (isAjaxRequest) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            
            PrintWriter writer = response.getWriter();
            RestResponse<Void> restResponse = RestResponse.unauthorized("인증이 필요합니다.");
            writer.write(objectMapper.writeValueAsString(restResponse));
            writer.flush();
		} else {
			response.sendRedirect("/login?error=required");
		}
	}
}
