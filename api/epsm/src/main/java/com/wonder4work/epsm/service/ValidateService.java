package com.wonder4work.epsm.service;

import com.wonder4work.epsm.domain.User;

/**
 * @author xiezengcheng
 * @date 2020-09-03
 */
public interface ValidateService {


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录校验是否通过
     */
    User login(String username, String password) throws Exception;

}
