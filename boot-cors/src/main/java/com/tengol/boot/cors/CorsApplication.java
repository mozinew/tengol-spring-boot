package com.tengol.boot.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CorsApplication
 *
 * @author dongrui
 * @date 2020/8/27 13:43
 */
@Slf4j
@SpringBootApplication
public class CorsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CorsApplication.class, args);
        log.info("CorsApplication started successfully ...");
    }
}
