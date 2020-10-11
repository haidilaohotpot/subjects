package com.wonder4work.webserviceclient.bean;

import lombok.Data;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
@Data
public class Weather {

    private Long weatherId;

    private String city;

    private String weather;

    public Weather() {
    }

    public Weather(Long weatherId, String city, String weather) {
        this.weatherId = weatherId;
        this.city = city;
        this.weather = weather;
    }
}
