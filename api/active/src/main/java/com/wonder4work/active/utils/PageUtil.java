package com.wonder4work.active.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-08-31
 */
public class PageUtil {

    public static PagedGridResult setterPagedGrid(Integer page, List<?> list) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
