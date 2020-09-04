package com.wonder4work.epsm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Data
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账户类别 0 管理员 1 普通用户
     */
    @TableField("account_type")
    private Integer accountType;

    /**
     * 用户类型 0 总经理 1 部门经理 2 职员
     */
    @TableField("user_type")
    private Integer userType;
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
     * 状态 0 删除 1 可用
     */
    private Integer status;


}
