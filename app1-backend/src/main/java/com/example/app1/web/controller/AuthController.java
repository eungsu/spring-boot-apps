package com.example.app1.web.controller;

import com.example.app1.service.AuthService;
import com.example.app1.service.UserService;
import com.example.app1.web.request.LoginRequest;
import com.example.app1.web.request.JwtResponse;
import com.example.app1.web.request.SignupRequest;
import com.example.app1.web.request.SignupResponse;
import com.example.app1.web.response.common.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<RestResponse<SignupResponse>>  signup(@Valid @RequestBody SignupRequest signupRequest) {
    SignupResponse signupResponse = userService.signup(signupRequest);

    return ResponseEntity.status(201).body(RestResponse.created("회원가입 완료", signupResponse));
  }

  @PostMapping("/login")
  public ResponseEntity<RestResponse<JwtResponse>> authenticateUser(@RequestBody LoginRequest loginRequest) {
    JwtResponse jwtResponse = authService.login(loginRequest);

    return ResponseEntity.ok(RestResponse.success(jwtResponse));
  }

  @GetMapping("/refresh")
  public ResponseEntity<RestResponse<JwtResponse>> refreshToken(@RequestParam String token) {
    JwtResponse jwtResponse = authService.refreshToken(token);

    return ResponseEntity.ok(RestResponse.success(jwtResponse));
  }

}
