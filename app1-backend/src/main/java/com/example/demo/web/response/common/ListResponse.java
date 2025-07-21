package com.example.demo.web.response.common;

import java.util.List;

import com.example.demo.util.Pagination;

import lombok.Getter;

@Getter
public class ListResponse<T> {
	private final List<T> items;
	private final Pagination paging;

	public ListResponse(List<T> items, Pagination paging) {
		this.items = items;
		this.paging = paging;
	}
}
