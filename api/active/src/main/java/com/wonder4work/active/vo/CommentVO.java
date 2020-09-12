package com.wonder4work.active.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-12
 */
@Data
public class CommentVO implements Serializable {


    private Integer id;

    private String name;

    private String img;

    private Integer userId;

    private Integer activityId;

    private String msg;

    private Date createTime;



}
