package com.wonder4work.epsm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.epsm.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
