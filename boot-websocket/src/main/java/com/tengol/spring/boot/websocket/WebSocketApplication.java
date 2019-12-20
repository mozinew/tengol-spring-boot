package com.tengol.spring.boot.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebSocketApplication
 *
 * @author dongrui
 * @date 2019/12/20 9:32
 */
@Slf4j
@SpringBootApplication
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
        log.info("WebSocketApplication start successfully ...");
    }
}
