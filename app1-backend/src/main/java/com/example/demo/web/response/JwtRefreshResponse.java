package com.example.demo.web.response;

import lombok.Getter;

@Getter
public class JwtRefreshResponse {
	private final String accessToken;
	private final long expiresIn;

	public JwtRefreshResponse(String accessToken, long expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}
}
