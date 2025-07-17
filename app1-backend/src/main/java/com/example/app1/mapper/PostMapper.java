package com.example.app1.mapper;

import com.example.app1.vo.Post;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {

  void insertPost(Post post);
  int getTotalRows();
  List<Post> getPosts(@Param("offset") int offset, @Param("rows") int rows);
}
