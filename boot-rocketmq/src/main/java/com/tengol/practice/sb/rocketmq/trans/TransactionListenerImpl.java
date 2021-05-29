package com.tengol.practice.sb.rocketmq.trans;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * TransactionListenerImpl
 *
 * @author dongrui
 * @date 2021/5/28 12:52
 */
@Slf4j
public class TransactionListenerImpl implements TransactionListener {
    /**
     * 发送 half 消息成功后，就会回调这个函数，因此，可以在这里执行本地事务
     *
     * @param message 发送的消息
     * @param o       操作对象
     * @return 事务消息处理结果（commit or rollback）
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 执行本地事务，依据执行结果判断是 commit 还是 rollback 事务消息
        try {
            // 接着根据本地一连串操作数据库的操作结果，确认是 commit 还是 rollback
            boolean result = execSQLResult();
            if (result) {
                // 处理成功，则提交事务消息
                log.info("提交 half 消息");
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                // 处理失败，则回滚事务消息
                log.info("回滚 half 消息");
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            log.error("订单系统处理失败，回滚 half 消息：{}", e.getMessage(), e);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    /**
     * 如果因为各种原因，没有 commit 或 rollback 事务消息，则 RocketMQ 会回调到该方法，询问如何处理 half 消息
     *
     * @param messageExt 发送的消息
     * @return 事务消息处理结果（commit or rollback）
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        // 查询本地事务是否执行成功
        int status = getStatus(messageExt.getTransactionId());

        // 根据本地事务的执行情况，选择执行 commit 或 rollback
        switch (status) {
            case 0:
                return LocalTransactionState.UNKNOW;
            case 1:
                return LocalTransactionState.COMMIT_MESSAGE;
            case 2:
                return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.ROLLBACK_MESSAGE;
    }


    private boolean execSQLResult() {
        int random = new Random().nextInt(10);
        log.info("当前随机值：{}", random);

        if (random > 3) {
            log.info("订单系统：订单更新成功！");
            return true;
        } else {
            log.info("订单系统：订单更新失败！");
            return false;
        }
    }

    private int getStatus(String transactionId) {
        // 依据最后一个字母随机判断，模拟随机性
        String lastWord = transactionId.substring(transactionId.length() - 1);
        List<String> errorList = Arrays.asList("e", "s", "b", "c", "z", "g", "1", "3", "5", "7", "9");

        return errorList.contains(lastWord) ? 2 : 1;
    }
}
