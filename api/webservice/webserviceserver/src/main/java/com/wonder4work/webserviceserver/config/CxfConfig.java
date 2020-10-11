package com.wonder4work.webserviceserver.config;

import com.wonder4work.webserviceserver.interceptor.AuthInterceptor;
import com.wonder4work.webserviceserver.service.WeatherService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.List;


/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
@Configuration
public class CxfConfig {

    @Autowired
    private WeatherService weatherService;


    @Bean
    public ServletRegistrationBean exfServletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public WeatherService weatherService() {
        return weatherService;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), weatherService());
        endpoint.publish("/api");
        // 添加拦截器 实现限流和安全可靠性
        endpoint.getInInterceptors().add(new AuthInterceptor("root", "root"));

        return endpoint;
    }

}
