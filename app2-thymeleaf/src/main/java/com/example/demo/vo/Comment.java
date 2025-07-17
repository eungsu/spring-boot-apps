package com.example.demo.vo;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Comment")
public class Comment {

	private int no;
	private int userNo;
	private int postNo;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	private User user;
}
