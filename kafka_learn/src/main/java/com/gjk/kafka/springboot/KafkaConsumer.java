package com.gjk.kafka.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * KafkaConsumer
 *
 * @author: gaojiankang
 * @date: 2023/8/25 11:27
 * @description:
 */
@Configuration
public class KafkaConsumer {

    @KafkaListener(topics = "first")
    public void testConsumer(String message){
        System.out.println(message);
    }
}
