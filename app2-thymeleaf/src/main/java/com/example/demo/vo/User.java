package com.example.demo.vo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("User")
public class User {

	private int no;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String tel;
	private String role;
	private boolean deleted;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
