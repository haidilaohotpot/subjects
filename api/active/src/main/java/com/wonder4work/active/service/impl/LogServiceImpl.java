package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.active.domain.Log;
import com.wonder4work.active.mapper.LogMapper;
import com.wonder4work.active.service.LogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
