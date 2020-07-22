package com.tengol.boot.hello.config;

import com.alibaba.fastjson.JSON;
import com.tengol.boot.hello.dto.Param;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloConfig
 *
 * @author dongrui
 * @date 2020/7/21 16:47
 */
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(value = {HelloProperties.class})
public class HelloConfig {
    private final HelloProperties helloProperties;

    @Bean
    public Param param(HelloProperties helloProperties){
        System.out.println(JSON.toJSONString(helloProperties));
        return new Param();
    }
}
