package com.example.demo.web.security;

import com.example.demo.web.response.common.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());

		Object exceptionAttribute = request.getAttribute("exception");
		Throwable cause;
		if (exceptionAttribute instanceof Throwable) {
			cause = (Throwable) exceptionAttribute;
		} else {
			cause = authException;
		}

		RestResponse<Void> errorResponse = getErrorResponse(cause);
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}

	private static RestResponse<Void> getErrorResponse(Throwable cause) {
		String errorMessage;

		if (cause instanceof SignatureException) {
			errorMessage = "유효하지 않는 JWT 서명";
		} else if (cause instanceof MalformedJwtException) {
			errorMessage = "유효하지 않는 JWT 토큰";
		} else if (cause instanceof ExpiredJwtException) {
			errorMessage = "만료된 JWT 토큰";
		} else if (cause instanceof UnsupportedJwtException) {
			errorMessage = "지원되지 않는 JWT 토큰";
		} else if (cause instanceof IllegalArgumentException) {
			errorMessage = "유효하지 않는 JWT 토큰";
		} else {
			errorMessage = "인증오류";
		}

		return RestResponse.unauthorized(errorMessage);
	}
}
