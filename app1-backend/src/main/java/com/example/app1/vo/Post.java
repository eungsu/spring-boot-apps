package com.example.app1.vo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("Post")
public class Post {
  private int no;
  private int userNo;
  private String title;
  private String content;
  private boolean deleted;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  private User user;
}
