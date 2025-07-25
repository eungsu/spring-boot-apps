package com.example.demo.web.controller;

import com.example.demo.service.PostService;
import com.example.demo.web.request.CommentCreateRequest;
import com.example.demo.web.request.PostCreateUpdateRequest;
import com.example.demo.web.response.CommentResponse;
import com.example.demo.web.response.PostDetailResponse;
import com.example.demo.web.response.PostListResponse;
import com.example.demo.web.response.common.ListResponse;
import com.example.demo.web.response.common.RestResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<RestResponse<?>> createPost(@Valid @RequestBody PostCreateUpdateRequest request,
			@AuthenticationPrincipal int userNo) {
		postService.createPost(request, userNo);

		return ResponseEntity.status(201)
				.body(RestResponse.created("게시글이 등록되었습니다.", null));
	}

	@GetMapping
	public ResponseEntity<RestResponse<ListResponse<PostListResponse>>> getPosts(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "rows", required = false, defaultValue = "10") int rows) {
		ListResponse<PostListResponse> data = postService.getPosts(page, rows);

		return ResponseEntity.ok(RestResponse.success(data));
	}
  
	@GetMapping("/{postNo}")
	public ResponseEntity<RestResponse<PostDetailResponse>> getPost(@PathVariable("postNo") int postNo) {
		PostDetailResponse data = postService.getPost(postNo);

		return ResponseEntity.ok(RestResponse.success(data));
	}
  
	@PutMapping("/{postNo}")
	public ResponseEntity<RestResponse<?>> updatePost(@PathVariable("postNo") int postNo,
			@RequestBody PostCreateUpdateRequest request,
			@AuthenticationPrincipal int userNo) {
		postService.updatePost(postNo, request, userNo);

		return ResponseEntity.ok()
				.body(RestResponse.success("게시글이 수정 되었습니다.", null));
	}
  
	@DeleteMapping("/{postNo}")
	public ResponseEntity<RestResponse<?>> deletePost(@PathVariable("postNo") int postNo,
			@AuthenticationPrincipal int userNo) {
		postService.deletePost(postNo, userNo);

		return ResponseEntity.ok()
				.body(RestResponse.success("게시글이 삭제처리 되었습니다.", null));
	}
	
	@PostMapping("/{postNo}/comments")
	public ResponseEntity<RestResponse<?>> addComment(@PathVariable("postNo") int postNo,
			@RequestBody CommentCreateRequest request,
			@AuthenticationPrincipal int userNo) {
		postService.addComment(request, postNo, userNo);
		
		return ResponseEntity.status(201)
				.body(RestResponse.created("댓글이 등록되었습니다.", null));
	}
	
	@GetMapping("/{postNo}/comments")
	public ResponseEntity<RestResponse<List<CommentResponse>>> getComments(@PathVariable("postNo") int postNo) {
		List<CommentResponse> data = postService.getComments(postNo);
		
		return ResponseEntity.ok(RestResponse.success(data));
	}
	
	@DeleteMapping("/{postNo}/comments/{commentNo}")
	public ResponseEntity<RestResponse<?>> deleteComment(@PathVariable("postNo") int postNo,
			@PathVariable("commentNo") int commentNo,
			@AuthenticationPrincipal int userNo) {
		postService.deleteComment(postNo, commentNo, userNo);
		
		return ResponseEntity.ok()
				.body(RestResponse.success("댓글 삭제되었습니다.", null));
	}
	
}
