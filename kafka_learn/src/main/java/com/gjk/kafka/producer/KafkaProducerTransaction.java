package com.gjk.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * KafkaProducerTransaction
 *
 * @author: gaojiankang
 * @date: 2023/3/28 14:39
 * @description:
 */
public class KafkaProducerTransaction {

    public static void main(String[] args) {
        //创建Kafka生产者配置对象
        Properties properties = new Properties();
        //设置bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //设置key/value 序列化方式
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置事务id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction_id_0");
        //初始化生产者
        Producer<String, String> producer = new KafkaProducer<>(properties);
        //初始化事务
        producer.initTransactions();
        producer.beginTransaction();
        try {
            for (int i = 0; i < 5; i++) {
                //发送消息
                producer.send(new ProducerRecord<>("tp_first", "KafkaProducerTransaction:" + i));
            }
            int f = 1 / 0;
            //提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
            //异常回滚事务
            producer.abortTransaction();
        } finally {
            producer.close();
        }
    }
}
