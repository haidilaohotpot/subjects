package com.wonder4work.epsm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.epsm.domain.LoginLog;
import com.wonder4work.epsm.domain.LoginLogBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志表 Mapper 接口
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Mapper
public interface LoginLogBOMapper{

    /**
     * 条件查询
     * @param queryMap 条件查询参数
     * @return List<LoginLogBO>
     */
    List<LoginLogBO> query(@Param("queryMap") Map<String, Object> queryMap);



}
