package com.example.demo.web.response;

import java.time.LocalDateTime;

import com.example.demo.vo.Post;

import lombok.Getter;

@Getter
public class PostDetailResponse {
	private final int postNo;
	private final String name;
	private final String title;
	private final String content;
	private final int viewCnt;
	private final int commentCnt;
	private final LocalDateTime createdDate;
	private final LocalDateTime updatedDate;
	
	public PostDetailResponse(Post post) {
		this.postNo = post.getNo();
		this.name = post.getUser().getNickname();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.viewCnt = post.getViewCnt();
		this.commentCnt = post.getCommentCnt();
		this.createdDate = post.getCreatedDate();
		this.updatedDate = post.getUpdatedDate();
	}
}
