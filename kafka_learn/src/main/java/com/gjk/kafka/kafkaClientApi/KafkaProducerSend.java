package com.gjk.kafka.kafkaClientApi;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * KafkaProducerSend
 *
 * @author: GJK
 * @date: 2023-03-24 11:27:10
 * @description:
 */
public class KafkaProducerSend {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        KafkaProducerSend kafkaProducer = new KafkaProducerSend();
//        kafkaProducer.sendAsync();
        kafkaProducer.sendAsyncCallback();
//        kafkaProducer.sendSync();
    }

    /**
     * 不带回调的异步发送
     */
    private void sendAsync() {
        Producer<String, String> producer = getKafkaProducer();
        //生产者发送数据
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("tp_first", "sendAsync" + i));
        }
        producer.close();
    }

    /**
     * 带回调的异步发送
     */
    private void sendAsyncCallback() throws InterruptedException {
        Producer<String, String> producer = getKafkaProducer();
        //生产者发送数据
        for (int i = 0; i < 5; i++) {
            // 该方法在 Producer 收到 ack 时调用，为异步调用
            producer.send(new ProducerRecord<>("tp_first", "sendAsyncCallback" + i), (metadata, exception) -> {
                if (exception == null) {
                    //没有异常
                    System.out.println("topic:" + metadata.topic() + ",partition:" + metadata.partition());
                } else {
                    //打印异常堆栈信息
                    exception.printStackTrace();
                }
            });

            // 延迟一会会看到数据发往不同分区
            Thread.sleep(2);
        }
        //关闭资源
        producer.close();
    }


    /**
     * 同步发送
     */
    private void sendSync() throws ExecutionException, InterruptedException {
        Producer<String, String> producer = getKafkaProducer();
        //生产者发送数据
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("tp_first", "sendSync" + i)).get();
        }
        producer.close();
    }

    /**
     * 获取kafka生产者对象
     *
     * @return
     */
    private static Producer<String, String> getKafkaProducer() {
        //创建配置对象
        Properties properties = new Properties();
        //设置bootstrap.servers
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //设置key-value序列化方式
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //创建生产者对象
        return new KafkaProducer<>(properties);
    }


}
