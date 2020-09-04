package com.wonder4work.epsm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.epsm.domain.User;
import com.wonder4work.epsm.domain.UserBO;
import com.wonder4work.epsm.utils.PagedGridResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户信息
     *
     * @param queryMap 条件参数:{loginName:登录名,name:姓名,userType:用户类型}
     * @param page 页码
     * @param pageSize 每页多少条
     * @return PagedGridResult
     */
    PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize);

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return User
     */
    User findByUserId(Integer userId);

    /**
     * 更新用户信息
     * @param userBO 前端用户信息数据
     * @return User
     */
    void update(UserBO userBO) throws Exception;


    /**
     * 添加新用户信息
     * @param userBO 前端用户信息数据
     * @return User
     */
    void create(UserBO userBO) throws Exception;

    /**
     * 根据用户ID删除用户信息
     * @param userId
     */
    void delete(Integer userId);

    /**
     * 批量删除用户信息
     * @param userIdList 用户ID数组
     */
    void deletes(List<Integer> userIdList);

    /**
     * 根据登录名或手机号查询
     * @param loginName 登录名
     * @param phone 手机号
     * @return List<User>
     */
    List<User> findByLoinNameOrPhone(String loginName, String phone);


    /**
     * 根据登录名和密码查询
     * @param loginName 登录名
     * @param password 密码
     * @return User
     */
    User findByLoinNameAndPassword(String loginName, String password);

    /**
     * 根据手机号和密码查询
     * @param phone 手机号
     * @param password 密码
     * @return User
     */
    User findByPhoneAndPassword(String phone, String password);

}
