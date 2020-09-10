package com.wonder4work.active.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-10
 */
@Data
@ApiModel("用户信息")
public class UserBO implements Serializable {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String vercode;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("头像")
    private String img;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 密码
     */
    @ApiModelProperty("重复密码")
    private String repassword;
    /**
     * 所属党支部
     */
    @ApiModelProperty("所属党支部")
    private Integer partyBranch;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;
    /**
     * 是否删除 0 否 1 是
     */
    private Integer isDel;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
