package com.gjk.kafka.springboot;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * KafkaProducer
 *
 * @author: gaojiankang
 * @date: 2023/8/25 11:23
 * @description:
 */
@RestController
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/send")
    public void testProducerSend(){
        kafkaTemplate.send("first","test");
    }

}
