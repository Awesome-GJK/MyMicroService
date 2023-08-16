package com.gjk.kafka.consumer;

import java.time.Duration;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * KafkaConsumerGroup
 *
 * @author: gaojiankang
 * @date: 2023/8/11 14:50
 * @description:
 */
public class KafkaConsumerGroup {



    /**
     * Kafka消费组消费消息
     *
     * @return
     */
    private static void consume() {
        //创建配置对象
        Properties properties = new Properties();

        //设置bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //设置key-value序列化方式
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置消费者组名称
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        //创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //设置要消费的主题
        consumer.subscribe(Stream.of("first").collect(Collectors.toList()));
        //设置消费某个主题的某个分区
        TopicPartition partition0 = new TopicPartition("first", 0);
        consumer.assign(Stream.of(partition0).collect(Collectors.toList()));

        //从Kafka中拉取数据打印
        while (true){
            //每1s中拉取一批数据
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            //打印拉取的数据
            records.forEach(record -> {
                System.out.println("topic:" + record.topic() + ",partition:" + record.partition() + ",offset:" + record.offset() + ",key:" + record.key() + ",value:" + record.value());
            });
        }
    }



}
