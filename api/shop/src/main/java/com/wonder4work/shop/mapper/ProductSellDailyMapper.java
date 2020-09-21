package com.wonder4work.shop.mapper;

import com.wonder4work.shop.domain.ProductSellDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-09-21
 */
@Mapper
public interface ProductSellDailyMapper {

    /**
     * 根据查询条件返回商品日销售的统计列表
     *
     * @param productSellDailyCondition
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ProductSellDaily> queryProductSellDailyList(
            @Param("productSellDailyCondition") ProductSellDaily productSellDailyCondition,
            @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);


    int insertProductSellDaily();


    int insertDefaultProductSellDaily();

}
