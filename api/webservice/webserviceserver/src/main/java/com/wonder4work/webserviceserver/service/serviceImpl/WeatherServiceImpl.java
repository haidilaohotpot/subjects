package com.wonder4work.webserviceserver.service.serviceImpl;

import com.wonder4work.webserviceserver.bean.Weather;
import com.wonder4work.webserviceserver.service.WeatherService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */

@Service
@WebService(serviceName = "WeatherService", // 与接口中指定的name一致
        targetNamespace = "http://service.wonder4work.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.wonder4work.webserviceserver.service.WeatherService"// 接口地址
)
public class WeatherServiceImpl implements WeatherService {


    @Override
    public List<Weather> listAllCityWeather() {

        Weather fzWeather = new Weather(1L, "福州", "晴");
        Weather xmWeather = new Weather(2L, "厦门", "多云");
        Weather shWeather = new Weather(3L, "上海", "雷阵雨");

        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(fzWeather);
        weatherList.add(xmWeather);
        weatherList.add(shWeather);

        return weatherList;
    }
}
