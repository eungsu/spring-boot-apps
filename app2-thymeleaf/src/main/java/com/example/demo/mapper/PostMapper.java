package com.example.demo.mapper;

import com.example.demo.vo.Post;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {

	void insertPost(Post post);
	void updatePost(Post post);
	void deletePost(int postNo);
	void incrementViewCnt(int postNo);
	void incrementCommentCnt(int postNo);
	void decrementCommentCnt(int postNo);
	int getTotalRows(String keyword);
	List<Post> getPosts(@Param("keyword") String keyword, 
			            @Param("offset") int offset, 
			            @Param("rows") int rows);
	Optional<Post> getPostByPostNo(int postNo);
	Optional<Integer> getPostUserNoByPostNo(int postNo);
}
