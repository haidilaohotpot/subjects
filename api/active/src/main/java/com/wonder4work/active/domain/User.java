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
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 所属党支部
     */
    @TableField("party_branch")
    private Integer partyBranch;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否删除 0 否 1 是
     */
    @TableField("is_del")
    private Integer isDel;
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


}
