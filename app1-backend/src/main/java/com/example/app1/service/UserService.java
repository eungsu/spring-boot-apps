package com.example.app1.service;

import com.example.app1.exception.AppException;
import com.example.app1.mapper.UserMapper;
import com.example.app1.vo.User;
import com.example.app1.web.request.SignupRequest;
import com.example.app1.web.request.SignupResponse;
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

  public SignupResponse signup(SignupRequest signupRequest) {
    if (userMapper.getUserByUsername(signupRequest.getUsername()) != null) {
      throw new AppException("사용할 수 없는 아이디입니다.");
    }
    if (userMapper.getUserByEmail(signupRequest.getEmail()) != null) {
      throw new AppException("사용할 수 없는 이메일입니다.");
    }

    User user = modelMapper.map(signupRequest, User.class);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userMapper.insertUser(user);

    User savedUser = userMapper.getUserByUsername(signupRequest.getUsername());

    return modelMapper.map(savedUser, SignupResponse.class);
  }
}
