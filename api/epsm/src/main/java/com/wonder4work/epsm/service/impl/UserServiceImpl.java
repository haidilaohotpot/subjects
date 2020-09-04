package com.wonder4work.epsm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.wonder4work.epsm.domain.User;
import com.wonder4work.epsm.domain.UserBO;
import com.wonder4work.epsm.mapper.UserMapper;
import com.wonder4work.epsm.service.UserService;
import com.wonder4work.epsm.utils.MD5Utils;
import com.wonder4work.epsm.utils.PageUtil;
import com.wonder4work.epsm.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> findByLoinNameOrPhone(String loginName, String phone) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName).or().eq("phone", phone);
        List<User> userList = this.list(queryWrapper);

        return userList;
    }

    @Override
    public User findByLoinNameAndPassword(String loginName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("login_name", loginName);
        queryMap.put("password", password);
        queryWrapper.allEq(queryMap);
        return this.getOne(queryWrapper);
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("phone", phone);
        queryMap.put("password", password);
        queryWrapper.allEq(queryMap);

        return this.getOne(queryWrapper);
    }

    @Override
    public void deletes(List<Integer> userIdList) {
        this.removeByIds(userIdList);
    }

    @Override
    public void delete(Integer userId) {
        this.removeById(userId);
    }

    @Override
    public void update(UserBO userBO) throws Exception {

        User updateUser = new User();
        BeanUtils.copyProperties(userBO,updateUser);
        updateUser.setStatus(1);
        updateUser.setUpdateTime(new Date());
        updateUser.setPassword(MD5Utils.getMD5Str(updateUser.getPassword()));
        this.updateById(updateUser);
    }

    @Override
    public void create(UserBO userBO) throws Exception {
        User insertUser = new User();
        BeanUtils.copyProperties(userBO,insertUser);
        insertUser.setStatus(1);
        insertUser.setCreateTime(new Date());
        insertUser.setUpdateTime(new Date());
        insertUser.setPassword(MD5Utils.getMD5Str(insertUser.getPassword()));
        this.save(insertUser);
    }


    @Override
    public User findByUserId(Integer userId) {
        User user = this.getById(userId);
        user.setPassword("");
        return user;
    }

    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        String loginName = (String) queryMap.get("loginName");
        String name = (String) queryMap.get("name");
        String userType = (String) queryMap.get("userType");
        if (StringUtils.isNotBlank(loginName)) {
            queryWrapper.like("login_name", loginName);
        }

        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }

        if (StringUtils.isNotBlank(userType)) {
            queryWrapper.eq("user_type", userType);
        }
        queryWrapper.orderByDesc("update_time");

        PageHelper.startPage(page, pageSize);
        List<User> userList = this.list(queryWrapper);
        userList.forEach(user -> {
            user.setPassword("");
        });
        return PageUtil.setterPagedGrid(page, userList);
    }
}
