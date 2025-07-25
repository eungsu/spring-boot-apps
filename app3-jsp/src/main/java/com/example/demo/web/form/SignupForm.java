package com.example.demo.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {

	@NotBlank(message = "아이디는 필수 입력값입니다.")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	private String password;
	
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "유효한 이메일 형식이 아닙니다.")
	private String email;
	
	private String nickname;
	
	private String tel;
}
