package com.example.demo.web.response.common;

import java.util.List;

import com.example.demo.util.Pagination;

import lombok.Getter;

@Getter
public class ListResponse<T> {
	private final List<T> items;
	private final int page;
	private final int totalRows;
	private final int totalPage;

	private ListResponse(List<T> items, int page, int totalRows, int totalPage) {
		this.items = items;
		this.page = page;
		this.totalRows = totalRows;
		this.totalPage = totalPage;
	}

	public static <T> ListResponse<T> toListResponse(List<T> items, Pagination pagination) {
		return new ListResponse<T>(items, pagination.getPage(), pagination.getTotalRows(), pagination.getTotalPage());
	}
}
