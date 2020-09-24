package com.tengol.spring.boot.kafka.hello;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * KafkaProducer
 *
 * @author dongrui
 * @date 2020/1/20 15:18
 */
public class HelloKafkaProducer {
    public static final String brokers = "114.67.82.110:9092";
    public static final String topic = "topic-hello-world";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers",brokers);

        //配置生产者的客户端参数，并创建 KafkaProducer 实例
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        //构建需要发送的消息
        ProducerRecord<String, String> message = new ProducerRecord<String, String>(topic, "10", "Hello world 10");

        //发送消息
        producer.send(message);
        System.out.println("消息发送成功");

        //关闭生产者客户端
        producer.close();
    }
}
