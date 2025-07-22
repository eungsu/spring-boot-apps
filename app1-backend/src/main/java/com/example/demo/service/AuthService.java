package com.example.demo.service;

import com.example.demo.exception.AppException;
import com.example.demo.mapper.RefreshTokenMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.RefreshToken;
import com.example.demo.vo.User;
import com.example.demo.web.request.LoginRequest;
import com.example.demo.web.response.JwtRefreshResponse;
import com.example.demo.web.response.JwtResponse;
import com.example.demo.web.security.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Value("${app.jwt.expirationInMs}")
	private long jwtExpirationInMs;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final RefreshTokenMapper refreshTokenMapper;
	private final UserMapper userMapper;

	public JwtResponse login(LoginRequest loginRequest) {
		User user = userMapper.getUserByUsername(loginRequest.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException("사용자 정보가 없습니다.");
		}
		if (user.isDeleted()) {
			throw new CredentialsExpiredException("삭제된 사용자 입니다.");
		}
		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("비밀번호가 올바르지 않습니다.");
		}
		
		String accessToken = jwtTokenProvider.generateToken(user.getNo(), user.getRole());
		RefreshToken refreshToken = generateRefreshToken(user.getNo());
		
		refreshTokenMapper.deleteRefreshTokenByUserNo(user.getNo());
		refreshTokenMapper.insertRefreshToken(refreshToken);
		
		return new JwtResponse(accessToken, refreshToken.getToken(), jwtExpirationInMs);
	}

	public JwtRefreshResponse refreshToken(String token) {
		RefreshToken refreshToken = refreshTokenMapper.getRefreshTokenByToken(token);
		if (refreshToken == null) {
			throw new AppException("refreshToken 정보가 없습니다.");
		}
		if (refreshToken.isExpired()) {
			throw new AppException("refreshToken이 만료되었습니다.");
		}
		
		User user = userMapper.getUserByUserNo(refreshToken.getUserNo());
		String accessToken = jwtTokenProvider.generateToken(user.getNo(), user.getRole());
		
		return new JwtRefreshResponse(accessToken, jwtExpirationInMs);
	}

	private RefreshToken generateRefreshToken(int userNo) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUserNo(userNo);
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setExpires(LocalDateTime.now().plusDays(1));

		return refreshToken;
	}
}
