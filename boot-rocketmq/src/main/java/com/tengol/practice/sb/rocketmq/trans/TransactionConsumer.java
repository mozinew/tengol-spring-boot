package com.tengol.practice.sb.rocketmq.trans;

import com.tengol.practice.sb.rocketmq.common.RocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * TransactionConsumer
 *
 * @author dongrui
 * @date 2021/5/29 10:03
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = RocketConstant.TOPIC_ORDER_PAY_SUCCESS, consumerGroup = RocketConstant.GROUP_RED)
public class TransactionConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("【消费端】消费到消息啦：{}", s);
    }
}
