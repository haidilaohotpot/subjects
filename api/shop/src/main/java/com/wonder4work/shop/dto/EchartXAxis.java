package com.wonder4work.shop.dto;

import java.util.HashSet;

/**
 * 迎合echart里的xAxis
 */
public class EchartXAxis {

    private String type = "category";

    private HashSet<String> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashSet<String> getData() {
        return data;
    }

    public void setData(HashSet<String> data) {
        this.data = data;
    }
}
