package com.example.demo.service;

import com.example.demo.exception.AppException;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.util.Pagination;
import com.example.demo.vo.Comment;
import com.example.demo.vo.Post;
import com.example.demo.web.request.CommentCreateRequest;
import com.example.demo.web.request.PostCreateUpdateRequest;
import com.example.demo.web.response.CommentResponse;
import com.example.demo.web.response.PostDetailResponse;
import com.example.demo.web.response.PostListResponse;
import com.example.demo.web.response.common.ListResponse;

import java.text.MessageFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

	private final ModelMapper modelMapper;
	private final PostMapper postMapper;
	private final CommentMapper commentMapper;
  
	public void createPost(PostCreateUpdateRequest request, int userNo) {
		Post post = modelMapper.map(request, Post.class);
		post.setUserNo(userNo);
	  
		postMapper.insertPost(post);
	}

	public ListResponse<PostListResponse> getPosts(int page, int rows) {
		int totalRows = postMapper.getTotalRows();
		Pagination pagination = new Pagination(page, totalRows, rows);

		if (page > pagination.getTotalPage() || page <= 0) {
			if (pagination.getTotalPage() == 1) {
				throw new AppException(MessageFormat.format("페이지 번호는 {0}만 가능합니다.", pagination.getTotalPage()));
			} else {
				throw new AppException(MessageFormat.format("페이지 번호는 {0} ~ {1} 범위의 값만 가능합니다.", 1, pagination.getTotalPage()));
			}
		}

		List<Post> posts = postMapper.getPosts(pagination.getOffset(), pagination.getRows());
		List<PostListResponse> items = posts.stream().map(PostListResponse::new).toList();

		return new ListResponse<PostListResponse>(items, pagination);
	}
  
	public PostDetailResponse getPost(int postNo) {
		Post post = postMapper.getPostByPostNo(postNo).orElseThrow(() -> new AppException("게시글을 찾을 수 없습니다."));
		
		return new PostDetailResponse(post);
	}
  
	public void updatePost(int postNo, PostCreateUpdateRequest request, int userNo) {
		int postedUserNo = postMapper.getPostUserNoByPostNo(postNo).orElseThrow(() -> new AppException("게시글이 존재하지 않습니다."));
		if (postedUserNo != userNo) {
			throw new AppException("다른 사용자가 작성한 게시글은 삭제할 수 없습니다.");
		}
		
		Post post = modelMapper.map(request, Post.class);
		post.setNo(postNo);
		post.setUserNo(userNo);
		
		postMapper.updatePost(post);
	}

	public void deletePost(int postNo, int userNo) {
		int postedUserNo = postMapper.getPostUserNoByPostNo(postNo).orElseThrow(() -> new AppException("게시글이 존재하지 않습니다."));
		if (postedUserNo != userNo) {
			throw new AppException("다른 사용자가 작성한 글은 삭제할 수 없습니다.");
		}
		
		postMapper.deletePost(postNo);
	}

	public List<CommentResponse> getComments(int postNo) {
		List<Comment> comments = commentMapper.getCommentsByPostNo(postNo);
		
		return comments.stream().map(CommentResponse::new).toList();
	}
	
	@Transactional
	public void addComment(CommentCreateRequest request, int postNo, int userNo) {
		Comment comment = modelMapper.map(request, Comment.class);
		comment.setPostNo(postNo);
		comment.setUserNo(userNo);
		
		commentMapper.insertComment(comment);
		postMapper.incrementCommentCnt(postNo);
	}
	
	@Transactional
	public void deleteComment(int postNo, int commentNo, int userNo) {
		Comment comment = commentMapper.getCommentByCommentNo(commentNo).orElseThrow(() -> new AppException("댓글이 존재하지 않습니다."));
		if (comment.getUserNo() != userNo) {
			throw new AppException("다른 사용자가 작성한 댓글은 삭제할 수 없습니다.");
		}
		
		commentMapper.deleteComment(commentNo);
		postMapper.decrementCommentCnt(postNo);
	}
}






















