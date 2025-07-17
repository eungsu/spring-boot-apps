package com.example.demo.web.response;

import java.time.LocalDateTime;

import com.example.demo.vo.Post;

import lombok.Getter;

@Getter
public class PostDetailResponse {
	private final int postNo;
	private final int userNo;
	private final String nickname;
	private final String title;
	private final String content;
	private final int commentCnt;
	private final boolean deleted;
	private final LocalDateTime createdDate;
	private final LocalDateTime updatedDate;
	
	public PostDetailResponse(Post post) {
		this.postNo = post.getNo();
		this.userNo = post.getUserNo();
		this.nickname = post.getUser().getNickname();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.commentCnt = post.getCommentCnt();
		this.deleted = post.isDeleted();
		this.createdDate = post.getCreatedDate();
		this.updatedDate = post.getUpdatedDate();
	}
}
