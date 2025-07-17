package com.example.app1.web.controller;

import com.example.app1.service.PostService;
import com.example.app1.web.response.common.ListResponse;
import com.example.app1.web.response.PostListResponse;
import com.example.app1.web.response.common.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping
  public ResponseEntity<RestResponse<ListResponse<PostListResponse>>> getPosts(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
      @RequestParam(name = "rows", required = false, defaultValue = "10") int rows) {
    ListResponse<PostListResponse> data = postService.getPosts(page, rows);

    return ResponseEntity.ok(RestResponse.success(data));
  }
}
