package com.wonder4work.kafka.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonder4work.kafka.domain.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaConsumer<String, String> consumer;

    @GetMapping("/send")
    public JSONResult send(String msg, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        Log log = new Log();
        log.setMsg(msg);
        log.setStatus(String.valueOf(response.getStatus()));
        log.setCreateTime(new Date());
        log.setUpdateTime(new Date());
        String objectToJson = JsonUtils.objectToJson(log);
        //使用kafka模板发送信息
        kafkaTemplate.send("log", "msg", objectToJson);
        return JSONResult.ok();
    }

    @GetMapping("/receive")
    public JSONResult receive() {

        consumer.subscribe(Arrays.asList("log"));
        //死循环，持续消费kafka

        List<Log> messageList = new ArrayList<>();
        //消费数据，并设置超时时间
        ConsumerRecords<String, String> records = consumer.poll(100);
        //Consumer message
        for (ConsumerRecord<String, String> record : records) {
            String value = record.value();
            Log log = JsonUtils.jsonToPojo(value, Log.class);
            messageList.add(log);
        }
        return JSONResult.ok(messageList);
    }


}
