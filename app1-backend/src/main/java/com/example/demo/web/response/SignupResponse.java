package com.example.demo.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
	private int user_no;
	private String username;
	private String role;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
}
