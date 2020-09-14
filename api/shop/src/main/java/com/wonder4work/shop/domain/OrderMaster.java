package com.wonder4work.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wonder4work.shop.enums.OrderStatusEnum;
import com.wonder4work.shop.enums.PayStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 * @author xiezengcheng
 */
@TableName("order_master")
@Data
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 订单id. */
    @TableId(value = "order_id",type = IdType.INPUT)
    private String orderId;

    /** 买家名字. */
    @TableField("buyer_name")
    private String buyerName;

    /** 买家手机号. */
    @TableField("buyer_phone")
    private String buyerPhone;

    /** 买家地址. */
    @TableField("buyer_address")
    private String buyerAddress;

    /** 买家微信Openid. */
    @TableField("buyer_openid")
    private String buyerOpenid;

    /** 订单总金额. */
    @TableField("order_amount")
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    @TableField("order_status")
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    @TableField("pay_status")
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    @TableField("create_time")
    private Date createTime;

    /** 更新时间. */
    @TableField("update_time")
    private Date updateTime;

}
