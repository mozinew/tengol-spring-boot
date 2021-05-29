package com.tengol.practice.sb.rocketmq.hello.service;

import com.tengol.practice.sb.rocketmq.common.RocketConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * ProducerDemo
 *
 * @author dongrui
 * @date 2021/5/28 10:53
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProducerDemoService {


    private RocketMQTemplate rocketMQTemplate;


    public void sendMessage(String message) {
        rocketMQTemplate.send(RocketConstant.DEFAULT_TOPIC, MessageBuilder.withPayload(message).build());
        log.info("消息发送成功：{}", message);
    }
}
