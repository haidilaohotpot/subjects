package com.wonder4work.service;

import com.wonder4work.bo.UserBO;
import com.wonder4work.util.PagedGridResult;

/**
 * @author xiezengcheng
 * @date 2020-08-06
 */
public interface UserService {

    /**
     * 添加一个新的用户
     * @param userBO
     */
    void create(UserBO userBO);


    /**
     * 更新用户
     * @param userBO
     */
    void update(UserBO userBO);


    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);

    /**
     * 检查员工代码是否已经存在
     * @param code
     * @return
     */
    boolean checkCode(String code);


    /**
     * 分页查询用户信息数据
     * @param page
     * @param pageSize
     * @param queryText
     * @return
     */
    PagedGridResult pageQuery(int page, int pageSize, String queryText);


}
