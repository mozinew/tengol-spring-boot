package com.tengol.practice.sb.rocketmq.hello.service;

import com.tengol.practice.sb.rocketmq.common.RocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * ConsumerDemo
 *
 * @author dongrui
 * @date 2021/5/28 11:02
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = RocketConstant.DEFAULT_TOPIC, consumerGroup = RocketConstant.DEFAULT_CONSUMER_GROUP)
public class ConsumerDemoService implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("【消费端】消费到消息啦：{}", s);
    }
}
