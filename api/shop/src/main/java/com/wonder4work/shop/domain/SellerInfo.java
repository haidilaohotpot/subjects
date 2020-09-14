package com.wonder4work.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商家信息
 * @author xiezengcheng
 */
@TableName("seller_info")
@Data
public class SellerInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "seller_id",type = IdType.INPUT)
    private String sellerId;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("openid")
    private String openid;
}
