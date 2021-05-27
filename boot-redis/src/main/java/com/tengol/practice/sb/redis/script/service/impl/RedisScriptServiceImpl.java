package com.tengol.practice.sb.redis.script.service.impl;

import com.tengol.practice.sb.redis.script.service.RedisScriptService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * RedisScriptServiceImpl
 *
 * @author dongrui
 * @date 2021/5/27 18:31
 */
@Service
public class RedisScriptServiceImpl implements RedisScriptService {
    /**
     * 最大消息序列号，超过则重置为 0
     */
    private static final long MAX_SN = 4294967295L;
    /**
     * 存储序列号的 Key
     */
    private static final String REDIS_KEY_SN = "HELLO::SN";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DefaultRedisScript<Long> redisScriptForSN;


    /**
     * 获取序列号，如果传入参数则直接返回，否则每次调用自增 1
     *
     * @param field        子业务标识
     * @param serialNumber 序列号，可以为空
     * @return 最新的序列号
     */
    @Override
    public long getSerialNumber(String field, @Nullable Long serialNumber) {
        if (Objects.nonNull(serialNumber)) {
            return serialNumber;
        }

        return getSerialNumByRedis(field);
    }

    /**
     * 获取连续的序列号，使用 lua 脚本保证并发安全
     *
     * @param field hash 结构的 field
     * @return 序列号
     */
    public long getSerialNumByRedis(String field) {

        List<String> keys = Arrays.asList(REDIS_KEY_SN, field);
        String[] args = {String.valueOf(MAX_SN), "1"};

        Long serialNumber = stringRedisTemplate.execute(redisScriptForSN, keys, args);
        if (Objects.isNull(serialNumber)) {
            return 0;
        }

        return serialNumber;
    }
}
