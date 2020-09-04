package com.wonder4work.epsm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.epsm.domain.LoginLog;
import com.wonder4work.epsm.domain.LoginLogBO;
import com.wonder4work.epsm.mapper.LoginLogBOMapper;
import com.wonder4work.epsm.mapper.LoginLogMapper;
import com.wonder4work.epsm.service.LoginLogService;
import com.wonder4work.epsm.utils.PageUtil;
import com.wonder4work.epsm.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogBOMapper loginLogBOMapper;

    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<LoginLogBO> loginLogBOList = loginLogBOMapper.query(queryMap);

        return PageUtil.setterPagedGrid(page, loginLogBOList);
    }

}
