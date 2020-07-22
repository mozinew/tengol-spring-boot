package com.tengol.spring.boot.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理 socket 连接
 *
 * @author dongrui
 * @date 2020/7/16 17:12
 */
@Slf4j
public class SocketManager {
    private static ConcurrentHashMap<String, WebSocketSession> socketMap = new ConcurrentHashMap<>(16);

    public static void add(String key, WebSocketSession webSocketSession) {
        log.info("连接添加成功：{}", key);
        socketMap.put(key, webSocketSession);
    }

    public static void remove(String key) {
        if (!socketMap.containsKey(key)) {
            throw new IllegalArgumentException("连接不存在：{}" + key);
        }
        socketMap.remove(key);
        log.info("连接删除成功：{}", key);
    }

    public static WebSocketSession get(String key) {
        log.info("获取连接：{}", key);
        return socketMap.get(key);
    }
}
