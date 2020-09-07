package com.wonder4work.active.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @since 1.0.0 2020/3/29
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 配置swagger2核心配置 docket
     * @return
     */
    @Bean
    public Docket createRestApi() {

        // 官方提供的文档地址 http://localhost:port/swagger-ui.html

        // /doc.html 第三方路径
        // 指定api类型为2
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 用于定义api文档汇总信息
                //指定controller包
                .select().apis(RequestHandlerSelectors.basePackage("com.wonder4work.active.controller"))
                // 所有的controller
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档页标题
                .title("课题8接口")
                .contact(new Contact("wonder4work", "http://www.wonder4work.com", "g.xiezch@ffcs.cn"))
                .description("课题7接口的api文档")
                //文档版本号
                .version("1.0.0")
                .termsOfServiceUrl("http://www.wonder4work.com")
                .build();
    }


}
