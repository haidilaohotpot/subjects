package cn.ffcs.mapper;

import cn.ffcs.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiezengcheng
 * @date 2020-08-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
