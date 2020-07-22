package com.tengol.spring.boot.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebSocketV2Application
 *
 * @author dongrui
 * @date 2019/12/20 13:38
 */
@Slf4j
@SpringBootApplication
public class WebSocketV2Application {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketV2Application.class, args);
        log.info("WebSocketV2Application start successfully ...");
    }
}
