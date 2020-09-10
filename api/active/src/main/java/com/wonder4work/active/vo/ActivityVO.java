package com.wonder4work.active.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-10
 */
@Data
public class ActivityVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 所属党支部
     */
    private String partyBranch;
    /**
     * 可见范围 0 不可见 1 部分可见 2 全部可见
     */
    private Integer visibleRange;
    /**
     * 报名名额
     */
    private Integer quota;
    /**
     * 活动主题
     */
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
    private Date activityStartTime;
    /**
     * 活动结束时间
     */
    private Date activityEndTime;
    /**
     * 报名开始时间
     */
    private Date signUpStartTime;
    /**
     * 报名结束时间
     */
    private Date signUpEndTime;
    /**
     * 活动要求
     */
    private String activityRequire;
    /**
     * 活动描述
     */
    private String activityDesc;
    /**
     * 活动状态 -1 已取消 0 报名已结束 1 报名中 2 进行中 3 活动已结束 4 未开始
     */
    private Integer activityStatus;
    /**
     * 报名人数
     */
    private Integer signUpNum;
    /**
     * 评论人数
     */
    private Integer commentNum;
    /**
     * 点赞人数
     */
    private Integer likeNum;
    /**
     * 活动封面
     */
    private String cover;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 状态 (备用)
     */
    private Integer status;

}
