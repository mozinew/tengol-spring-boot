package com.tengol.practice.sb.redis.script.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * LuaConfig
 *
 * @author dongrui
 * @date 2021/5/27 12:25
 */
@Configuration
public class LuaScriptConfig {

    /**
     * 读取生成序列号（Serial Number）的 Redis 脚本
     *
     * @return 脚本
     */
    @Bean
    public DefaultRedisScript<Long> redisScriptForSN() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/get-sn.lua")));

        return script;
    }
}
