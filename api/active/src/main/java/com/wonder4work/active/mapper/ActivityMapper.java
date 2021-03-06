package com.wonder4work.active.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.vo.ActivityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 条件查询活动信息
     * @param map 查询参数
     * @return List<ActivityVO>
     */
    List<ActivityVO> query(@Param("map")Map<String,Object> map);


}
