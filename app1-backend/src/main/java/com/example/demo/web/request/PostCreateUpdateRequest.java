package com.example.demo.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateUpdateRequest {
	@NotBlank(message = "제목은 필수 입력값입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수 입력값입니다.")
	private String content;
}
