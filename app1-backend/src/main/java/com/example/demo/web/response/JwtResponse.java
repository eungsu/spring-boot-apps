package com.example.demo.web.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import lombok.Getter;

@Getter
public class JwtResponse {
	private final String accessToken;
	private final String refreshToken;
	private final long expiresAt;

	public JwtResponse(String accessToken, String refreshToken, long expiresIn) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresAt = LocalDateTime.now().plus(expiresIn, ChronoUnit.MILLIS)
				.atZone(ZoneId.systemDefault())
				.toInstant()
				.toEpochMilli();
	}
}
