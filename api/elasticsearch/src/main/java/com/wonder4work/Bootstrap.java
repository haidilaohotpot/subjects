package com.wonder4work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author xiezengcheng
 * @date 2020-08-18
 */
@SpringBootApplication
//@EnableElasticsearchRepositories(basePackages = "com.wonder4work.repository")
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
