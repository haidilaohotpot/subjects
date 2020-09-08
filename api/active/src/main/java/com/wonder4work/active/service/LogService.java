package com.wonder4work.active.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.active.domain.Log;
import com.wonder4work.active.utils.PagedGridResult;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
public interface LogService extends IService<Log> {

    /**
     * 条件分页查询日志信息
     * @param queryMap 条件参数Map:{content:日志内容,api:接口,createTimeMin:创建时间最小值,createTimeMax:创建时间最大值}
     * @param page 页码
     * @param pageSize 每页显示多少条
     * @return PagedGridResult
     */
    PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize);
}
