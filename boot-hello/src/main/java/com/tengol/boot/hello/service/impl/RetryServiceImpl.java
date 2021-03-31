package com.tengol.boot.hello.service.impl;

import com.tengol.boot.hello.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * RetryServiceImpl
 *
 * @author dongrui
 * @date 2020/10/13 17:30
 */
@Slf4j
@Service
public class RetryServiceImpl implements RetryService {

    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 10000L, multiplier = 2)
    )
    @Override
    public void print() {
        log.info("hello retry ...");
        log.info(String.valueOf(1 / 0));

    }

    @Recover
    public void recover(Exception e, String param) {
        System.out.println("达到最大重试次数,或抛出了一个没有指定进行重试的异常:" + e.getMessage());
    }

}
