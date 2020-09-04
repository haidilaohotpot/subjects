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
 * 接口调用日志表

 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Data
@Accessors(chain = true)
@TableName("t_interface_log")
public class InterfaceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口调用日志主键
     */
    @TableId(value = "interface_log_id", type = IdType.AUTO)
    private Integer interfaceLogId;
    /**
     * 接口请求参数
     */
    @TableField("request_params")
    private String requestParams;
    /**
     * 接口响应内容
     */
    @TableField("response_content")
    private String responseContent;
    /**
     * 接口请求状态 0 失败 1 成功 
     */
    @TableField("request_status")
    private Integer requestStatus;
    /**
     * 接口响应状态 0 失败 1 成功
     */
    @TableField("response_status")
    private Integer responseStatus;
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
