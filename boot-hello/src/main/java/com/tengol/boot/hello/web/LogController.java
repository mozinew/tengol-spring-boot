package com.tengol.boot.hello.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 用途：学习日志配置 logback-spring.xml
 *
 * @author dongrui
 * @date 2020/9/25 15:38
 */
@Slf4j(topic = "log_ctl")
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/test")
    public String printLog(@RequestParam(name = "name", defaultValue = "Hello World") String name){

        // 打印日志
        log.trace("print trace log : {}" , name);
        log.debug("print debug log : {}" , name);
        log.info("print info log : {}" , name);
        log.warn("print warn log : {}" , name);
        log.error("print error log : {}" , name);

        return UUID.randomUUID().toString();
    }
}
