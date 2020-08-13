package cn.ffcs.service;

import cn.ffcs.bean.User;
import cn.ffcs.util.PagedGridResult;
import cn.ffcs.vo.UserVO;

import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-08-06
 */
public interface UserService {

    /**
     * 添加一个新的用户
     * @param userVO
     */
    void create(UserVO userVO);


    /**
     * 更新用户
     * @param userVO
     */
    void update(UserVO userVO);


    /**
     * 删除用户
     * @param userVO
     */
    void delete(UserVO userVO);


    /**
     *
     * @param page
     * @param pageSize
     * @param queryText
     * @return
     */
    PagedGridResult pageQuery(int page, int pageSize, String queryText);


    /**
     * 根据method执行不同的业务
     * @param userVO
     */
    void doService(UserVO userVO);

}
