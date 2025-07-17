package com.example.demo.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.demo.web.response.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {


		boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (isAjaxRequest) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            
            PrintWriter writer = response.getWriter();
            RestResponse<Void> restResponse = RestResponse.forbidden("접근 권한이 부족합니다.");
            writer.write(objectMapper.writeValueAsString(restResponse));
            writer.flush();
		} else {
			response.sendRedirect("/access-denied");
		}
	}
}
