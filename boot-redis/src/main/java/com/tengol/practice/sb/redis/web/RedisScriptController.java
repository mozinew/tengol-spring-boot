package com.tengol.practice.sb.redis.web;

import com.tengol.practice.sb.redis.script.service.RedisScriptService;
import com.tengol.practice.sb.redis.util.RestResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RedisScriptController
 *
 * @author dongrui
 * @date 2021/5/27 12:32
 */
@Slf4j
@RestController
@RequestMapping("/redis/script")
@AllArgsConstructor
public class RedisScriptController {

    private RedisScriptService redisScriptService;

    @GetMapping(value = "/sn", name = "获取序列号")
    public ResponseEntity<RestResult> getSerialNumber(@RequestParam("field") String field) {

        long serialNumber = redisScriptService.getSerialNumber(field, null);
        log.info("当前 {} 的序列号是：{}", field, serialNumber);

        return ResponseEntity.ok(RestResult.success(serialNumber));
    }
}
