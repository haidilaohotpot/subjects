package com.wonder4work.webserviceclient.client;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
@Slf4j
public class ClientTest {


    public static void main(String[] args) {


        WeatherService weatherService = new WeatherService_Service()
                .getWeatherServiceImplPort();

        List<Weather> weatherList = weatherService.listAllCityWeather();

        weatherList.forEach(weather -> {
            log.debug(weather.toString());
        });
    }

}
