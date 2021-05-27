package com.tengol.practice.sb.redis.script.service;

/**
 * RedisScriptService
 *
 * @author dongrui
 * @date 2021/5/27 12:34
 */
public interface RedisScriptService {

    /**
     * 获取序列号，如果传入参数则直接返回，否则每次调用自增 1
     *
     * @param field        子业务标识
     * @param serialNumber 序列号，可以为空
     * @return 最新的序列号
     */
    long getSerialNumber(String field, Long serialNumber);
}
