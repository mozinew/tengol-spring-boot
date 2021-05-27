package com.tengol.practice.sb.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RedisApplication
 *
 * @author dongrui
 * @date 2021/5/27 12:25
 */
@Slf4j
@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
        log.info("RedisApplication run successfully ......");
    }
}
