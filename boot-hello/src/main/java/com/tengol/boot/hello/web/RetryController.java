package com.tengol.boot.hello.web;

import com.tengol.boot.hello.service.RetryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * RetryController
 *
 * @author dongrui
 * @date 2020/10/13 17:29
 */
@Slf4j
@RestController
@RequestMapping("/retry")
@AllArgsConstructor
public class RetryController {

    private RetryService retryService;

    @GetMapping("/print")
    public String print() {
        log.info("retry invoked ...");
//        retryService.print();
        return UUID.randomUUID().toString();
    }
}
