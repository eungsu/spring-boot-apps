package com.example.demo.web.response;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import lombok.Getter;

@Getter
public class JwtRefreshResponse {
	private final String accessToken;
	private final long expiresAt;

	public JwtRefreshResponse(String accessToken, long expiresIn) {
		this.accessToken = accessToken;
		this.expiresAt = LocalDateTime.now().plus(expiresIn, ChronoUnit.MILLIS)
				.atZone(ZoneId.systemDefault())
				.toInstant()
				.toEpochMilli();
	}
}
