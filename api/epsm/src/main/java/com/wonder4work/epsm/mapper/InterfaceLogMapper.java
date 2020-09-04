package com.wonder4work.epsm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.epsm.domain.InterfaceLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 接口调用日志表
 Mapper 接口
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Mapper
public interface InterfaceLogMapper extends BaseMapper<InterfaceLog> {

}
