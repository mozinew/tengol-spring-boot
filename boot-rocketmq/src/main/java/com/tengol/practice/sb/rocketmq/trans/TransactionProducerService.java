package com.tengol.practice.sb.rocketmq.trans;

import com.tengol.practice.sb.common.util.ThreadPoolUtil;
import com.tengol.practice.sb.rocketmq.common.RocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;

/**
 * TransactionProducer
 *
 * @author dongrui
 * @date 2021/5/28 12:51
 */
@Slf4j
@Service
public class TransactionProducerService {
    private TransactionMQProducer producer;

    @PostConstruct
    public void init() {
        // 用来接收 RocketMQ 回调的一个监听器接口
        // 这里会实现执行订单本地事务、commit、rollback、回调查询等逻辑
        TransactionListener transactionListener = new TransactionListenerImpl();

        // 创建一个事务消息的生产者，为其指定一个生产者分组（名字任意）
        producer = new TransactionMQProducer(RocketConstant.DEFAULT_PRODUCER_GROUP);

        // 指定一个线程池，用来处理 RocketMQ 的回调请求
        producer.setExecutorService(ThreadPoolUtil.getThreadPool());

        // 给事务消息生产者设置对应的回调函数
        producer.setTransactionListener(transactionListener);

        // 启动这个事务消息生产者
        try {
            producer.start();
            log.info("TransactionMQProducer start successfully ...");
        } catch (MQClientException e) {
            log.error("TransactionMQProducer start failure : {}", e.getErrorMessage(), e);
            throw new RuntimeException("TransactionMQProducer start failure");
        }
    }

    @PreDestroy
    public void stop() {
        producer.shutdown();
        log.info("TransactionMQProducer destroy successfully ...");
    }

    public void sendMessage(String msg) throws UnsupportedEncodingException {

        // 构造一条订单支付成功的消息，指定发送的 Topic
        Message message = new Message(RocketConstant.TOPIC_ORDER_PAY_SUCCESS, RocketConstant.TEST_TAG,
                RocketConstant.TEST_KEY, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 将消息作为 half 消息发送出去
        try {
            producer.sendMessageInTransaction(message, null);
        } catch (MQClientException e) {
            // 对 half 消息发送失败的处理，订单系统要执行回滚逻辑，比如：触发退款操作 + 订单状态更新为“已关闭”
            log.error("发送 Half 消息失败：{}", e.getErrorMessage(), e);
            log.info("发送 MQ 消息失败，款项已退回，已更新数据库关闭订单！！！");
        }
    }
}
