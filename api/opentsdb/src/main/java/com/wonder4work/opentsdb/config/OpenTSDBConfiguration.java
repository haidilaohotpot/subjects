package com.wonder4work.opentsdb.config;

import org.apache.http.nio.reactor.IOReactorException;
import org.opentsdb.client.OpenTSDBClient;
import org.opentsdb.client.OpenTSDBClientFactory;
import org.opentsdb.client.OpenTSDBConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiezengcheng
 * @date 2020-08-27
 */
@Configuration
public class OpenTSDBConfiguration {

    @Value("${opentsdb.host}")
    public String host;

    @Value("${opentsdb.port}")
    public Integer post;


    @Bean
    public OpenTSDBClient openTSDBClient() {

        OpenTSDBConfig config = OpenTSDBConfig.address(host, post)
                .config();
        try {
            OpenTSDBClient client = OpenTSDBClientFactory.connect(config);
            return client;
        } catch (IOReactorException e) {
            e.printStackTrace();
            return null;
        }
    }

}
