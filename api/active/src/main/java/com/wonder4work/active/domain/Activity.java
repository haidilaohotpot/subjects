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
@TableName("t_activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 所属党支部
     */
    @TableField("party_branch")
    private Integer partyBranch;
    /**
     * 可见范围 0 不可见 1 部分可见 2 全部可见
     */
    @TableField("visible_range")
    private Integer visibleRange;
    /**
     * 报名名额
     */
    private Integer quota;
    /**
     * 活动主题
     */
    @TableField("activity_theme")
    private String activityTheme;
    /**
     * 活动摘要
     */
    private String summary;
    /**
     * 活动地址
     */
    private String address;
    /**
     * 活动开始时间
     */
    @TableField("activity_start_time")
    private Date activityStartTime;
    /**
     * 活动结束时间
     */
    @TableField("activity_end_time")
    private Date activityEndTime;
    /**
     * 报名开始时间
     */
    @TableField("sign_up_start_time")
    private Date signUpStartTime;
    /**
     * 报名结束时间
     */
    @TableField("sign_up_end_time")
    private Date signUpEndTime;
    /**
     * 活动要求
     */
    @TableField("activity_require")
    private String activityRequire;
    /**
     * 活动描述
     */
    @TableField("activity_desc")
    private String activityDesc;
    /**
     * 活动状态 -1 已取消 0 报名已结束 1 报名中 2 进行中 3 活动已结束 4 未开始
     */
    @TableField("activity_status")
    private Integer activityStatus;
    /**
     * 报名人数
     */
    @TableField("sign_up_num")
    private Integer signUpNum;
    /**
     * 评论人数
     */
    @TableField("comment_num")
    private Integer commentNum;
    /**
     * 点赞人数
     */
    @TableField("like_num")
    private Integer likeNum;
    /**
     * 活动封面
     */
    private String cover;
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
     * 状态 (备用)
     */
    private Integer status;
    /**
     * 是否删除 0 否 1 是
     */
    @TableField("is_del")
    private Integer isDel;


}
