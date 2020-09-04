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
public class LoginLogBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer loginLogId;

    private String loginUser;

    private Date createTime;

    private Date updateTime;

    private String ip;


}
