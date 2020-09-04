package com.wonder4work.epsm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.epsm.domain.LoginLog;
import com.wonder4work.epsm.utils.PagedGridResult;

import java.util.Map;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 分页查询
     *
     * @param queryMap 条件参数
     * @param page 页码
     * @param pageSize 每页多少条
     * @return PagedGridResult
     */
    PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize);
}
