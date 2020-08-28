package com.tengol.boot.cors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CorsConfig
 *
 * Spring Boot 解决跨域问题，只需要把 CorsFilter 添加到容器中就行。
 *
 * @author dongrui
 * @date 2020/8/27 15:31
 */
@Configuration
public class CorsConfig {
    // 设置允许跨域的源
    private static String[] origins = new String[]{
            "127.0.0.1:8080",
            "localhost:8080",
            "localhost:8081",
            "google.com"
    };


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域的源
        addAllowedOrigins(false, config);
        // 设置允许的请求方法，* 代表所有
        config.addAllowedMethod("*");
        // 设置允许的请求头信息，* 代表所有
        config.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    private void addAllowedOrigins(boolean isAll, CorsConfiguration corsConfiguration) {
        if (isAll) {
            corsConfiguration.addAllowedOrigin("/**");
        } else {
            for (String origin : origins) {
                corsConfiguration.addAllowedOrigin("http://" + origin);
                corsConfiguration.addAllowedOrigin("https://" + origin);
            }
        }
    }
}
