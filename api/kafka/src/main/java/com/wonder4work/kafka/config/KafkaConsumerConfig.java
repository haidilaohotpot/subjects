package com.wonder4work.kafka.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author xiezengcheng
 * @date 2020-08-26
 */
@Configuration
public class KafkaConsumerConfig {


    @Bean
    public KafkaConsumer kafkaConsumer() {

        KafkaConsumer<String, String> consumer;

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.43.25:9092");
        props.put("group.id", "0");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "15000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //创建消费者对象
        consumer = new KafkaConsumer<String, String>(props);

        return consumer;
    }

}
