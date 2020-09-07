package com.wonder4work.active.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Data
@Accessors(chain = true)
@TableName("t_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 详情
     */
    private String content;
    /**
     * 接口
     */
    private String api;
    /**
     * 关联用户
     */
    @TableField("create_user")
    private Integer createUser;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
