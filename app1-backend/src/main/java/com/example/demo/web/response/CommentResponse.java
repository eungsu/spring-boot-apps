package com.example.demo.web.response;

import java.time.LocalDateTime;

import com.example.demo.vo.Comment;

import lombok.Getter;

@Getter
public class CommentResponse {

	private final int commentNo;
	private final String content;
	private final LocalDateTime createdDate;
	private final String name;
	
	public CommentResponse(Comment comment) {
		this.commentNo = comment.getNo();
		this.content = comment.getContent();
		this.createdDate = comment.getCreatedDate();
		this.name = comment.getUser().getNickname();
	}
}
