package com.wonder4work.shop.dto;

import java.util.List;

/**
 * 迎合echart里的series值
 *
 * @author zengcheng.xie
 */
public class EchartSeries {

    private String name;
    private String type = "bar";
    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }


}
