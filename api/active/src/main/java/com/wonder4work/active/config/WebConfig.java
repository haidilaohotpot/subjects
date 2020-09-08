package com.wonder4work.active.config;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiezengcheng
 * @date 2020-09-07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    //注册servlet 验证码
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha");
        servlet.addInitParameter("kaptcha.border", "no");
        servlet.addInitParameter("kaptcha.textproducer.font.color", "red");
        servlet.addInitParameter("kaptcha.image.width", "135");
        servlet.addInitParameter("kaptcha.textproducer.char.string", "ACDEFHKGPRSTWX23456789");
        servlet.addInitParameter("kaptcha.image.height", "50");
        servlet.addInitParameter("kaptcha.textproducer.font.size", "43");
        servlet.addInitParameter("kaptcha.noise.color", "black");
        servlet.addInitParameter("kaptcha.textproducer.char.length", "4");
        servlet.addInitParameter("kaptcha.textproducer.font.names", "Arial");
        return servlet;
    }


}
