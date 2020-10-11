package com.wonder4work.webserviceclient.service;

import com.wonder4work.webserviceclient.bean.Weather;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */

@WebService(name = "WeatherService", // 暴露服务名称
        targetNamespace = "http://service.wonder4work.com"// 命名空间,一般是接口的包名倒序
)
public interface WeatherService {

    /**
     * 查询所有的天气信息
     * @return
     * @author xiezengcheng
     */
    @WebMethod
    List<Weather> listAllCityWeather();

}
