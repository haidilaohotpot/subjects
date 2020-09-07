package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.mapper.ActivityMapper;
import com.wonder4work.active.service.ActivityService;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

}
