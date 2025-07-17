package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AppException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;
import com.example.demo.web.form.SignupForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final ModelMapper modelMapper;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	public void signup(SignupForm signupForm) {
		if (userMapper.getUserByUsername(signupForm.getUsername()) != null) {
			throw new AppException("사용할 수 없는 아이디입니다.");
		}
		if (userMapper.getUserByEmail(signupForm.getEmail()) != null) {
			throw new AppException("사용할 수 없는 이메일입니다.");			
		}
		
		User user = modelMapper.map(signupForm, User.class);
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		
		userMapper.insertUser(user);
	}
}
