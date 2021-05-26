package com.tengol.spring.boot.bbc.md;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MultiDataSourceApplication
 *
 * @author dongrui
 * @date 2021/3/31 17:40
 */
@Slf4j
@SpringBootApplication
public class MultiDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceApplication.class, args);
        log.info("MultiDataSourceApplication start successfully ...");
    }
}
