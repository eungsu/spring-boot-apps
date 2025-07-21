package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public class Pagination {

	private int page;
	@JsonIgnore
	private int block;
	@JsonIgnore
	private final int rows;
	@JsonIgnore
	private final int pagesPerBlock = 5;
	@JsonIgnore
	private int offset;
	private final int totalRows;
	private int totalPage;
	@JsonIgnore
	private int totalBlock;
	private int beginPage;
	private int endPage;
	private int prevPage;
	private int nextPage;
	private boolean isFirst;
	private boolean isLast;

	public Pagination(int page, int totalRows) {
		this.page = page;
		this.totalRows = totalRows;
		this.rows = 10;
		init();
	}

	public Pagination(int page, int totalRows, int rows) {
		this.page = page;
		this.totalRows = totalRows;
		this.rows = rows;
		init();
	}

	private void init() {
		if (page < 1) {
			page = 1;
		}
		offset = (page - 1) * rows;
		totalPage = Math.ceilDiv(totalRows, rows);
		if (page > totalPage) {
			page = totalPage;
		}
		totalBlock = Math.ceilDiv(totalPage, pagesPerBlock);
		block = Math.ceilDiv(page, pagesPerBlock);
		beginPage = (block - 1)*pagesPerBlock + 1;
		endPage = block*pagesPerBlock;
		if (block == totalBlock) {
			endPage = totalPage;
		}
		prevPage = page - 1;
		nextPage = page + 1;
		isFirst = page == 1;
		isLast = page == totalPage;		
	}
}