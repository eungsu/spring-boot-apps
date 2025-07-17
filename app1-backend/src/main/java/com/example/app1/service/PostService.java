package com.example.app1.service;

import com.example.app1.exception.AppException;
import com.example.app1.mapper.PostMapper;
import com.example.app1.util.Pagination;
import com.example.app1.vo.Post;
import com.example.app1.web.response.common.ListResponse;
import com.example.app1.web.response.PostListResponse;
import java.text.MessageFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostMapper postMapper;
  private final ModelMapper modelMapper;

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

    return ListResponse.toListResponse(items, pagination);
  }
}
