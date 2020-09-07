package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.active.domain.User;
import com.wonder4work.active.mapper.UserMapper;
import com.wonder4work.active.service.UserService;
import org.springframework.stereotype.Service;

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

}
