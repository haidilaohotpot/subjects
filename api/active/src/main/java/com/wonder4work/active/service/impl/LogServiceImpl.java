package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.active.domain.Log;
import com.wonder4work.active.mapper.LogMapper;
import com.wonder4work.active.service.LogService;
import com.wonder4work.active.utils.PageUtil;
import com.wonder4work.active.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {


    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();

        String content = (String) queryMap.get("content");
        String api = (String) queryMap.get("api");
        String createTimeMin = (String) queryMap.get("createTimeMin");
        String createTimeMax = (String) queryMap.get("createTimeMax");

        if (StringUtils.isNotBlank(content)) {
            queryWrapper.like("content", content);
        }

        if (StringUtils.isNotBlank(api)) {
            queryWrapper.like("api", api);
        }

        if (StringUtils.isNotBlank(createTimeMin)) {
            queryWrapper.ge("create_time", createTimeMin);
        }

        if (StringUtils.isNotBlank(createTimeMax)) {
            queryWrapper.le("create_time", createTimeMax);
        }

        queryWrapper.orderByDesc("create_time");

        PageHelper.startPage(page, pageSize);
        List<Log> logList = this.list(queryWrapper);

        return PageUtil.setterPagedGrid(page, logList);
    }


}
