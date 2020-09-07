package com.wonder4work.active.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @since 1.0.0 2020/3/29
 * 跨域配置
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        // 跨域配置基本信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        // 凭证 是否发送cookie信息
        corsConfiguration.setAllowCredentials(true);
        // 设置允许请求的方式
        corsConfiguration.addAllowedMethod("*");
        // 设置允许的header
        corsConfiguration.addAllowedHeader("*");

        // 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        // 返回定义好的source
        return new CorsFilter(corsSource);

    }

    /**
     * 解决问题：
     * There was an unexpected error (type=Internal Server Error, status=500).
     * An invalid domain [.xxx.com] was specified for this cookie
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return (factory) -> factory.addContextCustomizers(
                (context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
    }

}
