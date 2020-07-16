package com.tengol.spring.boot.kafka.hello;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;

/**
 * HelloKafkaConsumer
 *
 * @author dongrui
 * @date 2020/1/20 17:09
 */
public class HelloKafkaConsumer {
    public static final String brokers = "114.67.82.110:9092";
    public static final String topic = "topic-hello-world";
    public static final String groupId = "group.hello";

    public static void main(String[] args) {
        //消费者客户端连接属性
        Properties properties = getProperties();
        //创建一个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        //循环消费消息
        boolean isQuit = false;
        while (!isQuit){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> r : records) {
                System.out.println(r.key() + "," + r.value());
                if(Objects.nonNull(r.value()) && r.value().equals("quit-1")){
                    System.out.println("客户端退出");
                    isQuit = true;
                    break;
                }
            }
        }
    }

    private static Properties getProperties(){
        Properties properties = new Properties();
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers",brokers);
        //设置消费组的名称
        properties.put("group.id",groupId);

        return properties;
    }
}
