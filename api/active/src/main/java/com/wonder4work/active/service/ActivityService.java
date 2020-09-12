package com.wonder4work.active.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.utils.PagedGridResult;
import com.wonder4work.active.vo.CommentVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 条件分页查询活动信息
     *
     * @param queryMap 查询参数 queryMap:{partyBranch:所属党支部,activityTheme:活动主题,activityStatus:活动状态}
     * @param page     页码
     * @param pageSize 每页显示多少条
     * @return PagedGridResult
     */
    PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize);


    /**
     * 条件分页查询活动报名人员信息
     *
     * @param queryMap 查询参数 queryMap:{partyBranch:所属党支部,name:姓名}
     * @param page     页码
     * @param pageSize 每页显示多少条
     * @return PagedGridResult
     */
    PagedGridResult signUpDetail(Map<String, Object> queryMap, Integer page, Integer pageSize);

    /**
     * 添加新活动
     *
     * @param activity 活动
     */
    void create(Activity activity);

    /**
     * 将活动状态别为取消
     *
     * @param activityId 活动ID
     */
    void cancel(Integer activityId);

    /**
     * 更新活动信息
     *
     * @param activity 活动
     */
    void update(Activity activity);

    /**
     * 报名
     * @param userId 用户ID
     * @param activityId 活动ID
     */
    void join(Integer userId, Integer activityId);

    /**
     * 取消报名
     * @param userId 用户ID
     * @param activityId 活动ID
     */
    void cancel(Integer userId, Integer activityId);

    /**
     * 查询用户是否报名此活动
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 报名：true 没有报名:false
     */
    boolean checkUserIsJoin(Integer userId,Integer activityId);

    /**
     * 给活动点赞
     * @param activityId 活动ID
     */
    void like(Integer activityId);


    /**
     * 查询活动评论信息
     *
     * @param map
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryComment(Map<String,Object> map,Integer page,Integer pageSize);

    /**
     * 根据ID删除评论
     * @param id
     * @param activityId
     */
    void deleteComment(Integer id,Integer activityId);

    /**
     *
     * @param commentVO
     */
    CommentVO comment(CommentVO commentVO);

}
