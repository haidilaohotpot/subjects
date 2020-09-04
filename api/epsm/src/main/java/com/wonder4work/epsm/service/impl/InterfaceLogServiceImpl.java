package com.wonder4work.epsm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.epsm.domain.InterfaceLog;
import com.wonder4work.epsm.mapper.InterfaceLogMapper;
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
public class InterfaceLogServiceImpl extends ServiceImpl<InterfaceLogMapper, InterfaceLog> implements InterfaceLogService {

    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {
        QueryWrapper<InterfaceLog> queryWrapper = new QueryWrapper<>();


        String responseContent = (String) queryMap.get("responseContent");
        String responseStatus = (String) queryMap.get("responseStatus");
        String updateTimeMin = (String) queryMap.get("updateTimeMin");
        String updateTimeMax = (String) queryMap.get("updateTimeMax");

        if (StringUtils.isNotBlank(responseContent)) {
            queryWrapper.like("response_content", responseContent);
        }

        if (StringUtils.isNotBlank(responseStatus)) {
            queryWrapper.eq("response_status", responseStatus);
        }

        if (StringUtils.isNotBlank(updateTimeMin)) {
            queryWrapper.ge("update_time", updateTimeMin);
        }

        if (StringUtils.isNotBlank(updateTimeMax)) {
            queryWrapper.le("update_time", updateTimeMax);
        }

        queryWrapper.orderByDesc("update_time");

        PageHelper.startPage(page, pageSize);

        List<InterfaceLog> interfaceLogList = this.list(queryWrapper);

        return PageUtil.setterPagedGrid(page, interfaceLogList);
    }
}
