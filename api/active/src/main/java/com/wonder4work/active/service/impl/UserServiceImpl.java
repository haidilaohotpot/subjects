package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.active.bo.UserBO;
import com.wonder4work.active.domain.User;
import com.wonder4work.active.mapper.UserMapper;
import com.wonder4work.active.service.UserService;
import com.wonder4work.active.utils.MD5Utils;
import com.wonder4work.active.vo.UserVO;
import org.springframework.beans.BeanUtils;
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
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User findByPhone(String phone) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        User one = this.getOne(queryWrapper);


        return one;
    }

    @Override
    public UserVO login(String username, String password) throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("phone", username);
        map.put("password", MD5Utils.getMD5Str(password));

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map);
        User dbUser = this.getOne(queryWrapper);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(dbUser, userVO);
        return userVO;
    }


    @Override
    public UserVO registe(UserBO userBO) throws Exception {

        User user = new User();
        BeanUtils.copyProperties(userBO, user);

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(1);
        user.setId(0);
        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        boolean save = this.save(user);

        UserVO userVO
                = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }


}
