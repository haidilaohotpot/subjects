package com.wonder4work.webserviceserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
@Data
public class Weather implements Serializable {

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
