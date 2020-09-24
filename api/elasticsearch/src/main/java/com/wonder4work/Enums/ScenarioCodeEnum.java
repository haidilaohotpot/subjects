package com.wonder4work.Enums;

/**
 * 活动状态枚举类 -1 已取消 0 报名已结束 1 报名中 2 进行中 3 活动已结束 4 未开始
 * @author xiezengcheng
 * @date 2020-09-10
 */

//    scenarioCodes['10111010001'] = '4G业务新装受理开通';
//    scenarioCodes['11111014001'] = '流量阀值提醒';
//    scenarioCodes['11111014002'] = '欠费停机提醒';
//    scenarioCodes['10111011001'] = '移动用户集团直充';
//    scenarioCodes['10111010002'] = '4G用户可选包';
//    scenarioCodes['11111017501'] = '';
//    scenarioCodes['11111017502'] = '';

public enum ScenarioCodeEnum {

    CODE_10111010001("4G业务新装受理开通","10111010001"),
    CODE_11111014001("流量阀值提醒","11111014001"),
    CODE_11111014002("欠费停机提醒","11111014002"),
    CODE_10111011001("移动用户集团直充","10111011001"),
    CODE_10111010002("4G用户可选包","10111010002");


    private String msg;

    private String value;

    ScenarioCodeEnum(String msg, String value) {
        this.msg = msg;
        this.value = value;
    }

    public static boolean contains(String value) {
        ScenarioCodeEnum[] values = ScenarioCodeEnum.values();
        for (ScenarioCodeEnum scenarioCodeEnum : values) {

            if (scenarioCodeEnum.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String getMsgForValue(String value) {
        ScenarioCodeEnum[] values = ScenarioCodeEnum.values();
        for (ScenarioCodeEnum scenarioCodeEnum : values) {

            if (scenarioCodeEnum.getValue().equals(value)) {
                return scenarioCodeEnum.getMsg();
            }
        }

        return value;
    }

    public static String getValueForMsg(String msg) {
        ScenarioCodeEnum[] values = ScenarioCodeEnum.values();
        for (ScenarioCodeEnum scenarioCodeEnum : values) {

            if (scenarioCodeEnum.getMsg().equals(msg)) {
                return scenarioCodeEnum.getValue();
            }
        }

        return msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
