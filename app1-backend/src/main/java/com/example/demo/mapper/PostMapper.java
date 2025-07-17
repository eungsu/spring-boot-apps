package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.Post;

@Mapper
public interface PostMapper {

	void insertPost(Post post);
	void updatePost(Post post);
	void deletePost(int postNo);
	int getTotalRows();
	List<Post> getPosts(@Param("offset") int offset, @Param("rows") int rows);
	Post getPostByPostNo(int postNo);
	Optional<Integer> getPostUserNoByPostNo(int postNo);
}
