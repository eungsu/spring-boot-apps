package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Comment;

@Mapper
public interface CommentMapper {

	List<Comment> getCommentsByPostNo(int postNo);
	Optional<Comment> getCommentByCommentNo(int commentNo);
	void insertComment(Comment comment);
	void deleteComment(int commentNo);
	void updateComment(Comment comment);
	
}
