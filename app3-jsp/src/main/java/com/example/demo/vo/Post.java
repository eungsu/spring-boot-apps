package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Post")
public class Post {
	private int no;
	private int userNo;
	private String title;
	private String content;
	private int viewCnt;
	private int commentCnt;
	private boolean deleted;
	private Date createdDate;
	private Date updatedDate;

	private User user;
}