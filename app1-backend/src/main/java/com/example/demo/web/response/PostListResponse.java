package com.example.demo.web.response;

import java.time.LocalDateTime;

import com.example.demo.vo.Post;

import lombok.Getter;

@Getter
public class PostListResponse {
	private final int postNo;
	private final int userNo;
	private final String title;
	private final int commentCnt;
	private final LocalDateTime createdDate;
	private final String name;

	public PostListResponse(Post post) {
		this.postNo = post.getNo();
		this.userNo = post.getUserNo();
		this.title = post.getTitle();
		this.commentCnt = post.getCommentCnt();
		this.createdDate = post.getCreatedDate();
		this.name = post.getUser().getNickname();
	}
}
