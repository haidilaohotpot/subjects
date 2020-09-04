package com.wonder4work.kafka.service;

import com.wonder4work.kafka.controller.PagedGridResult;
import com.wonder4work.kafka.domain.Log;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */
public interface LogService {

    /**
     * 查询日志信息
     * @param page
     * @param pageSize
     * @param queryText
     * @return
     */
    PagedGridResult query(String queryText, Integer page, Integer pageSize);


    /**
     * 添加一条日志
     * @param log
     * @return
     */
    Log create(Log log);

}
