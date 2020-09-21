package com.wonder4work.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 顾客消费的商品映射
 */
@Getter
@Setter
public class ProductSellDaily {

    private Long id;
    //销量
    private Integer total;

    //商品信息
    private ProductInfo product;

    //哪天的销量  精确到天
    private Date createTime;
}
