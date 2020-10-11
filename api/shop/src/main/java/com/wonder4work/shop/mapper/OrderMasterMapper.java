package com.wonder4work.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.shop.domain.OrderMaster;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiezengcheng
 * @date 2020-09-16
 */
@Mapper
public interface OrderMasterMapper extends BaseMapper<OrderMaster> {

    void finishOrderAuto();

}
