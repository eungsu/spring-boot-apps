package com.example.demo.dto;

import java.util.List;

import com.example.demo.util.Pagination;

import lombok.Getter;

@Getter
public class ListDto<T> {

	private List<T> items;
	private Pagination paging;
	
	public ListDto(List<T> items, Pagination paging) {
		this.items = items;
		this.paging = paging;
	}
}
