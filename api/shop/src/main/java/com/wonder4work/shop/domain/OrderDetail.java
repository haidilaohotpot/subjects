package com.wonder4work.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品订单详情
 *
 * @author xiezengcheng
 */
@TableName("order_detail")
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detail_id",type = IdType.INPUT)
    private String detailId;

    /** 订单id. */
    @TableField("order_id")
    private String orderId;

    /** 商品id. */
    @TableField("product_id")
    private String productId;

    /** 商品名称. */
    @TableField("product_name")
    private String productName;

    /** 商品单价. */
    @TableField("product_price")
    private BigDecimal productPrice;

    /** 商品数量. */
    @TableField("product_quantity")
    private Integer productQuantity;

    /** 商品小图. */
    @TableField("product_icon")
    private String productIcon;
}
