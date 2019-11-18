package com.tengol.spring.boot.schedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * HelloTaskSchedule
 *
 * @author dongrui
 * @date 2019/11/18 17:29
 */
@Slf4j
//@Configuration
public class HelloTaskSchedule {
    @Scheduled(cron = "0/5 * * * * ?")
    public void sayHello() {
        log.info("Hello World on {}", System.currentTimeMillis());
    }
}
