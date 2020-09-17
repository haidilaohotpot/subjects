package com.wonder4work.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wonder4work.shop.enums.ProductStatusEnum;
import com.wonder4work.shop.utils.EnumUtil;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author xiezengcheng
 */
@TableName("product_info")
@Data
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id",type = IdType.INPUT)
    private String productId;

    /** 名字. */
    @TableField("product_name")
    private String productName;

    /** 单价. */
    @TableField("product_price")
    private BigDecimal productPrice;

    /** 库存. */
    @TableField("product_stock")
    private Integer productStock;

    /** 描述. */
    @TableField("product_description")
    private String productDescription;

    /** 小图. */
    @TableField("product_icon")
    private String productIcon;

    /** 状态, 0正常1下架. */
    @TableField("product_status")
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    @TableField("category_type")
    private Integer categoryType;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

    /**
     * 图片链接加host拼接成完整 url
     * @param host
     * @return
     */
    public ProductInfo addImageHost(String host) {
        if (productIcon.startsWith("//") || productIcon.startsWith("http")) {
            return this;
        }

        if (!host.startsWith("http")) {
            host = "//" + host;
        }
        if (!host.endsWith("/")) {
            host = host + "/";
        }
        productIcon = host + productIcon;
        return this;
    }
}
