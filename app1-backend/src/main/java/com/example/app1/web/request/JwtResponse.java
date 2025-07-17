package com.example.app1.web.request;

import lombok.Getter;

@Getter
public class JwtResponse {

  private final String accessToken;
  private final String refreshToken;
  private final long expiresIn;
  private final String tokenType = "Bearer";

  public JwtResponse(String accessToken, String refreshToken, long expiresIn) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.expiresIn = expiresIn;
  }
}
