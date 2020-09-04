package com.wonder4work.epsm.domain;


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
 * 登录日志表
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Data
@Accessors(chain = true)
@TableName("t_login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志主键
     */
    @TableId(value = "login_log_id", type = IdType.AUTO)
    private Integer loginLogId;

    /**
     * 登录用户
     */
    @TableField("login_user")
    private Integer loginUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 用户IP
     */
    private String ip;


}
