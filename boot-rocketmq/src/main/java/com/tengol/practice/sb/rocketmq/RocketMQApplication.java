package com.tengol.practice.sb.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RocketMQApplication
 *
 * @author dongrui
 * @date 2021/5/28 10:50
 */
@Slf4j
@SpringBootApplication
public class RocketMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(RocketMQApplication.class, args);
        log.info("RocketMQApplication run successfully ...");
    }
}
