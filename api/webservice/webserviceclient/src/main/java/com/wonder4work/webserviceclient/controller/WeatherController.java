package com.wonder4work.webserviceclient.controller;

import com.wonder4work.webserviceclient.bean.Weather;
import com.wonder4work.webserviceclient.service.WeatherService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    /**
     * webservice接口地址
     */
    private static final String ADDRESS = "http://localhost:8080/services/api?wsdl";

    /**
     * 第一种调用方式
     * @return
     */
    @GetMapping
    public List<Weather> listAllCityWeather() {

        try {

            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(ADDRESS);
            //添加用户名密码拦截器
            //jaxWsProxyFactoryBean.getOutInterceptors().add(new LoginInterceptor("root","admin"));;
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(WeatherService.class);
            // 创建一个代理接口实现
            WeatherService weatherService = (WeatherService) jaxWsProxyFactoryBean.create();
            // 调用代理接口的方法调用并返回结果
            return weatherService.listAllCityWeather();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Weather>();
        }

    }

}
