package com.gjk.kafka.kafkaClientApi;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * MyProducerPartitioner
 *
 * @author: gaojiankang
 * @date: 2023/3/24 16:28
 * @description:
 */
public class MyProducerPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String msg = value.toString();
        if (msg.contains("test")) {
            return 1;
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        //指定bootstrap.servers
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //指定key/value序列化方式
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //指定我们自己定义的分区器
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyProducerPartitioner.class.getName());
        //构建生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //发送消息
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                //发送的value带有test
                String value = "test-MyProducerPartitioner-" + i;
                producer.send(new ProducerRecord<>("tp_first", value), (metadata, exception) -> {
                    if (exception == null) {
                        System.out.println(value + "-> topic:" + metadata.topic() + ",partition:" + metadata.partition());
                    } else {
                        exception.printStackTrace();
                    }
                });
            } else {
                //发送的value不带有test
                String value = "MyProducerPartitioner-" + i;
                producer.send(new ProducerRecord<>("tp_first", value), (metadata, exception) -> {
                    if (exception == null) {
                        System.out.println(value + "-> topic:" + metadata.topic() + ",partition:" + metadata.partition());
                    } else {
                        exception.printStackTrace();
                    }
                });
            }
            Thread.sleep(2);
        }
        //关闭资源
        producer.close();
    }
}
