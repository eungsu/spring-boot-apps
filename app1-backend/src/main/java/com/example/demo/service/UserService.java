package com.example.demo.service;

import com.example.demo.exception.AppException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;
import com.example.demo.web.request.SignupRequest;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	public void signup(SignupRequest signupRequest) {
		if (userMapper.getUserByUsername(signupRequest.getUsername()) != null) {
			throw new AppException("사용할 수 없는 아이디입니다.");
		}
		if (userMapper.getUserByEmail(signupRequest.getEmail()) != null) {
			throw new AppException("사용할 수 없는 이메일입니다.");
		}

		User user = modelMapper.map(signupRequest, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userMapper.insertUser(user);
	}
}
