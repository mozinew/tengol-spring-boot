package com.tengol.boot.hello.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * HelloProperties
 *
 * @author dongrui
 * @date 2020/7/21 16:35
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String userName;
    private String password;
    private String userAddress;
}
