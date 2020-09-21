package com.wonder4work.shop.service.impl;

import com.wonder4work.shop.domain.ProductSellDaily;
import com.wonder4work.shop.mapper.ProductSellDailyMapper;
import com.wonder4work.shop.service.ProductSellDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSellDailyServiceImpl  implements ProductSellDailyService {

    @Autowired
    private ProductSellDailyMapper productSellDailyMapper;

    @Override
    public void dailyCalculate() {


        //定期统计每个商品的销售量
        productSellDailyMapper.insertProductSellDaily();

        //统计销量为0的
        productSellDailyMapper.insertDefaultProductSellDaily();

    }


    @Override
    public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDaily, Long beginTime, Long endTime) {
        return productSellDailyMapper.queryProductSellDailyList(productSellDaily, beginTime, endTime);
    }
}
