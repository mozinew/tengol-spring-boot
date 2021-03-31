package com.tengol.boot.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * HelloApplication
 *
 * @author dongrui
 * @date 2020/3/20 14:06
 */
@Slf4j
@EnableRetry
@SpringBootApplication
public class HelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        log.info("HelloApplication start successfully ......");
    }
}
