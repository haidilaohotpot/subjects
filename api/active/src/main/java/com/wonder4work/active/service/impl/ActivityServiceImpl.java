package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.domain.Log;
import com.wonder4work.active.mapper.ActivityMapper;
import com.wonder4work.active.service.ActivityService;
import com.wonder4work.active.utils.PageUtil;
import com.wonder4work.active.utils.PagedGridResult;
import io.swagger.models.auth.In;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {


    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();

        String partyBranch = (String) queryMap.get("partyBranch");
        String activityTheme = (String) queryMap.get("activityTheme");
        String activityStatus = (String) queryMap.get("activityStatus");

        if (StringUtils.isNotBlank(partyBranch)) {
            queryWrapper.like("party_branch", partyBranch);
        }

        if (StringUtils.isNotBlank(activityTheme)) {
            queryWrapper.like("activity_theme", activityTheme);
        }
        if (StringUtils.isNotBlank(activityStatus)) {
            queryWrapper.like("activity_status", activityStatus);
        }


        queryWrapper.orderByDesc("create_time");

        PageHelper.startPage(page, pageSize);
        List<Activity> logList = this.list(queryWrapper);

        return PageUtil.setterPagedGrid(page, logList);
    }

}
