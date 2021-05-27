package com.tengol.practice.sb.redis;

import com.tengol.practice.sb.redis.script.service.RedisScriptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * RedisScriptTest
 *
 * @author dongrui
 * @date 2021/5/27 12:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisScriptTest {
    @Resource
    private RedisScriptService redisScriptService;

    @Test
    public void testGetSNByLua(){

    }

    @Test
    public void testGetSNByMultiTread(){

    }
}
