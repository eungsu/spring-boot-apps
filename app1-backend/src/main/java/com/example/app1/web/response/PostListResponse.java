package com.example.app1.web.response;

import com.example.app1.vo.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostListResponse {

  private final int postNo;
  private final int userNo;
  private final String title;
  @JsonFormat(pattern = "yyyy-MM-dd")
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
