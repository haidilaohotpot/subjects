package com.wonder4work.kafka.consumer;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */

import com.wonder4work.kafka.domain.Log;
import com.wonder4work.kafka.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 接受消息
 * @author xiezengcheng
 */
@Slf4j
@Component
public class LogConsumer {

    private final Integer MAX_SIZE = 2;

    @Autowired
    private LogService logService;

    /**
     * 定义此消费者接收topics = "log"的消息，与controller中的topic对应上即可
     *
     * @param consumer 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
//    @KafkaListener(topics = "log")
//    public void receive(ConsumerRecord<?, ?> consumer) {
//        log.info("{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
//        Log log = new Log();
//        String value = consumer.value().toString();
//
//        log.setStatus("200");
//        log.setMsg(value);
//        logService.create(log);
//
//    }


}
