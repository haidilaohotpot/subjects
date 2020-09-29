package com.wonder4work.opentsdb.controller;

import lombok.Data;

import java.util.List;

/**
 */
@Data
public class PagedGridResult {
    // 当前页数
    private int page = 1;
    // 总页数
    private int total = 20;
    // 总记录数
    private long records = 20;

    // 每行显示的内容
    private List<?> rows;


}
