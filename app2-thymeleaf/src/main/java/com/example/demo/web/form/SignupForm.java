package com.example.demo.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {

	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "아이디는 영어 대소문자, 숫자 포함 3글자 이상 20글자 이하여야 합니다.")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$", message = "아이디는 영어 대소문자, 숫자를 하나이상 포함하고 8글자 이상 20글자 이하여야 합니다.")
	private String password;
	
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "유효한 이메일 형식이 아닙니다.")
	private String email;
	
	@Pattern(regexp = "^^$|^[가-힣]{2,}$", message = "이름은 한글 2글자이상 입력해야 합니다.")
	private String nickname;
	
	@Pattern(regexp = "^^$|^\\d{3}-\\d{4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	private String tel;
}
