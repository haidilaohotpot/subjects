package com.wonder4work.epsm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.epsm.domain.Exception;
import com.wonder4work.epsm.domain.InterfaceLog;
import com.wonder4work.epsm.mapper.ExceptionMapper;
import com.wonder4work.epsm.mapper.InterfaceLogMapper;
import com.wonder4work.epsm.service.ExceptionService;
import com.wonder4work.epsm.service.InterfaceLogService;
import com.wonder4work.epsm.utils.PageUtil;
import com.wonder4work.epsm.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 接口调用日志表
 服务实现类
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Service
public class ExceptionServiceImpl extends ServiceImpl<ExceptionMapper, Exception> implements ExceptionService {

    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {
        QueryWrapper<Exception> queryWrapper = new QueryWrapper<>();

        String msg = (String) queryMap.get("msg");
        String updateTimeMin = (String) queryMap.get("updateTimeMin");
        String updateTimeMax = (String) queryMap.get("updateTimeMax");

        if (StringUtils.isNotBlank(msg)) {
            queryWrapper.like("msg", msg);
        }

        if (StringUtils.isNotBlank(updateTimeMin)) {
            queryWrapper.ge("update_time", updateTimeMin);
        }

        if (StringUtils.isNotBlank(updateTimeMax)) {
            queryWrapper.le("update_time", updateTimeMax);
        }

        queryWrapper.orderByDesc("update_time");

        PageHelper.startPage(page, pageSize);
        List<Exception> exceptionList = this.list(queryWrapper);

        return PageUtil.setterPagedGrid(page, exceptionList);
    }


}
