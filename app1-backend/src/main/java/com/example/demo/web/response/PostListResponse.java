package com.example.demo.web.response;

import java.time.LocalDateTime;

import com.example.demo.vo.Post;

import lombok.Getter;

@Getter
public class PostListResponse {
	private final int postNo;
	private final int userNo;
	private final String title;
	private final LocalDateTime createdDate;
	private final String nickname;

	public PostListResponse(Post post) {
		this.postNo = post.getNo();
		this.userNo = post.getUserNo();
		this.title = post.getTitle();
		this.createdDate = post.getCreatedDate();
		this.nickname = post.getUser().getNickname();
	}
}
