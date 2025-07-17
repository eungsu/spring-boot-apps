package com.example.app1.util;

import lombok.Getter;

@Getter
public class Pagination {

  private int page;
  private final int rows;
  private final int totalRows;
  private int totalPage;
  private int offset;

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
    totalPage = Math.ceilDiv(totalRows, rows);
    offset = (page - 1) * rows;
  }
}