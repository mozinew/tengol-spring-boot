package com.tengol.spring.boot.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ValidatorApplication
 *
 * @author dongrui
 * @date 2019/12/9 15:24
 */
@Slf4j
@SpringBootApplication
public class ValidatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidatorApplication.class, args);
        log.info("start successfully ...");
    }
}
