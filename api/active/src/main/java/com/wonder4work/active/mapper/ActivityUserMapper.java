package com.wonder4work.active.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.active.ActiveApplication;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.vo.ActivityUserVO;
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
public interface ActivityUserMapper extends BaseMapper<ActivityUserVO> {

    /**
     * 条件查询活动报名人员信息
     * @param map 查询参数
     * @return List<ActivityUserVO>
     */
    List<ActivityUserVO> query(@Param("map") Map<String, Object> map);

    /**
     * 参加互动
     * @param map 查询参数
     */
    void join(@Param("map") Map<String, Object> map);

    /**
     * 取消活动
     * @param map 查询参数
     */
    void cancel(@Param("map") Map<String, Object> map);

    /**
     * 查询用户是否报名此活动
     * @param map 查询参数
     * @return 报名数
     */
    Integer checkUserIsJoin(@Param("map") Map<String, Object> map);
}
