package com.wonder4work.shop.service;


import com.wonder4work.shop.domain.ProductSellDaily;

import java.util.List;

public interface ProductSellDailyService {

    //每日定时对所有商品销量统计
    void dailyCalculate();

    /**
     * 根据查询条件返回商品日销售的统计列表
     */
    List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDaily, Long beginTime, Long endTime);

}
