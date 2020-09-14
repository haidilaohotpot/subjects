package com.wonder4work.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品类目
 *
 * @author xiezengcheng
 */
@TableName("product_category")
@Data
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 类目id. */
    @TableId(value = "`category_id` ", type = IdType.AUTO)
    private Integer categoryId;

    /** 类目名字. */
    @TableField("category_name")
    private String categoryName;

    /** 类目编号. */
    @TableField("category_type")
    private Integer categoryType;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
