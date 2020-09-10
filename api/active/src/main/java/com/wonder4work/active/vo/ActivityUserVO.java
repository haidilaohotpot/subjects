package com.wonder4work.active.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-10
 */
@Data
public class ActivityUserVO implements Serializable {

    private Integer id;

    private Integer activityId;

    private Integer userId;

    private String img;

    private String name;

    private String partyBranch;

    private Date createTime;

    private Date updateTime;


}
