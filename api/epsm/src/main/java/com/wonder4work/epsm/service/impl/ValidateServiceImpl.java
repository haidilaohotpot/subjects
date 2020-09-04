package com.wonder4work.epsm.service.impl;

import com.wonder4work.epsm.domain.User;
import com.wonder4work.epsm.service.UserService;
import com.wonder4work.epsm.service.ValidateService;
import com.wonder4work.epsm.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiezengcheng
 * @date 2020-09-03
 */
@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private UserService userService;

    @Override
    public User login(String username, String password) throws Exception {

        String md5PasswordStr = MD5Utils.getMD5Str(password);
        User byLoinNameAndPassword = userService.findByLoinNameAndPassword(username, md5PasswordStr);

        if (byLoinNameAndPassword != null && byLoinNameAndPassword.getUserId() != null) {
            return byLoinNameAndPassword;
        }

        User byPhoneAndPassword = userService.findByPhoneAndPassword(username, md5PasswordStr);

        return byPhoneAndPassword;

    }

}
