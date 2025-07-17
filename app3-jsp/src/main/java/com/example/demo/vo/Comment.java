package com.example.demo.vo;

import java.util.Date;

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
	private Date createdDate;
	private Date updatedDate;

	private User user;
}
