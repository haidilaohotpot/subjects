package com.wonder4work.active.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.active.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
