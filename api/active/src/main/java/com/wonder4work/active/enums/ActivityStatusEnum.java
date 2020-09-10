package com.wonder4work.active.enums;

import lombok.Data;

/**
 * 活动状态枚举类 -1 已取消 0 报名已结束 1 报名中 2 进行中 3 活动已结束 4 未开始
 * @author xiezengcheng
 * @date 2020-09-10
 */

public enum  ActivityStatusEnum {

    CANCEL("已取消",-1),
    SIGN_UP_END("报名已结束",0),
    SIGN_UP_ING("报名中",1),
    ACTIVITY_ING("进行中",2),
    ACTIVITY_END("活动已结束",3),
    ACTIVITY_NO_START("活动未开始",4);

    private String msg;

    private Integer value;

    ActivityStatusEnum(String msg, Integer value) {
        this.msg = msg;
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}
