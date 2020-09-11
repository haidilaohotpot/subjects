package com.wonder4work.active.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.active.bo.UserBO;
import com.wonder4work.active.domain.User;
import com.wonder4work.active.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param username 手机号
     * @param password 密码
     * @return UserVO
     */
    UserVO login(String username, String password) throws Exception;

    /**
     * 注册
     * @param userBO 用户信息
     * @return UserVO
     */
    UserVO registe(UserBO userBO) throws Exception;

    /**
     * 通过手机号查找用户信息
     * @param phone 手机号
     * @return User
     */
    User findByPhone(String phone);

}
