package com.tengol.practice.sb.rocketmq.web;

import com.tengol.practice.sb.rocketmq.hello.service.ProducerDemoService;
import com.tengol.practice.sb.rocketmq.trans.TransactionProducerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * HelloController
 *
 * @author dongrui
 * @date 2021/5/28 10:51
 */
@RestController
@RequestMapping("/hello/rocket")
@AllArgsConstructor
public class HelloController {
    private ProducerDemoService producerDemoService;
    private TransactionProducerService transactionProducerService;

    @GetMapping(value = "/send", name = "开始生产消息")
    public String sendMessage(@RequestParam("msg") String msg) {

        producerDemoService.sendMessage(msg);

        return "欧耶，消息发送成功";
    }

    @GetMapping(value = "/tx-send", name = "开始生产消息")
    public String sendTransactionMessage(@RequestParam("msg") String msg) throws UnsupportedEncodingException {

        transactionProducerService.sendMessage(msg);

        return "欧耶，消息发送成功";
    }
}
