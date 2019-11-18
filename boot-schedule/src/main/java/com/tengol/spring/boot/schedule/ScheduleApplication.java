package com.tengol.spring.boot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ScheduleApplication
 *
 * @author dongrui
 * @date 2019/11/18 17:17
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class ScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
        log.info("start successfully ...");
    }
}
