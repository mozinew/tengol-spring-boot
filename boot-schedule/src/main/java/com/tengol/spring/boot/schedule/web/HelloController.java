package com.tengol.spring.boot.schedule.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author dongrui
 * @date 2019/11/18 17:21
 */
@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public ResponseEntity<String> sayHello(@RequestParam("name") String name) {
        log.info("hello ,  {}", name);
        return ResponseEntity.ok("hello " + name);
    }
}
