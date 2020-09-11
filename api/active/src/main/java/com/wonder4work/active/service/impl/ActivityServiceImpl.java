package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.domain.Log;
import com.wonder4work.active.enums.ActivityStatusEnum;
import com.wonder4work.active.mapper.ActivityMapper;
import com.wonder4work.active.mapper.ActivityUserMapper;
import com.wonder4work.active.service.ActivityService;
import com.wonder4work.active.utils.PageUtil;
import com.wonder4work.active.utils.PagedGridResult;
import com.wonder4work.active.vo.ActivityUserVO;
import com.wonder4work.active.vo.ActivityVO;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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
@Slf4j
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityUserMapper activityUserMapper;

    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {


        String partyBranch = (String) queryMap.get("partyBranch");
        String activityTheme = (String) queryMap.get("activityTheme");
        String activityStatus = (String) queryMap.get("activityStatus");
        String userId = (String) queryMap.get("userId");

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(userId)){
            map.put("userId", userId);
        }
        if (StringUtils.isNotBlank(partyBranch)) {
            map.put("partyBranch", partyBranch);
        }

        if (StringUtils.isNotBlank(activityTheme)) {
            map.put("activityTheme", activityTheme);
        }

        if (StringUtils.isNotBlank(activityStatus)) {
            map.put("activityStatus", activityStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<ActivityVO> activityVOList = this.baseMapper.query(map);

        return PageUtil.setterPagedGrid(page, activityVOList);
    }

    @Override
    public PagedGridResult signUpDetail(Map<String, Object> queryMap, Integer page, Integer pageSize) {
        String partyBranch = (String) queryMap.get("partyBranch");
        String name = (String) queryMap.get("name");
        String activityId = (String) queryMap.get("activityId");

        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isNotBlank(partyBranch)) {
            map.put("partyBranch", partyBranch);
        }

        if (StringUtils.isNotBlank(name)) {
            map.put("activityTheme", name);
        }

        if (StringUtils.isNotBlank(activityId)) {
            map.put("activityId", activityId);
        }

        PageHelper.startPage(page, pageSize);

        List<ActivityUserVO> activityUserVOList = activityUserMapper.query(map);

        return PageUtil.setterPagedGrid(page, activityUserVOList);
    }

    @Override
    public void create(Activity activity) {
        log.info(activity.toString());
        activity.setActivityStatus(4);
        activity.setCommentNum(0);
        activity.setIsDel(0);
        activity.setLikeNum(0);
        activity.setSignUpNum(0);
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        if (StringUtils.isBlank(activity.getCover())) {
            activity.setCover("http://www.wonder4work.com/image/logo.png");
        }
        this.save(activity);
    }

    @Override
    public void update(Activity activity) {
        log.info(activity.toString());
        activity.setUpdateTime(new Date());

        this.updateById(activity);
    }




    @Override
    public void cancel(Integer activityId) {

        Activity activity = this.getById(activityId);
        activity.setActivityStatus(ActivityStatusEnum.CANCEL.getValue());
        this.updateById(activity);
    }
}
