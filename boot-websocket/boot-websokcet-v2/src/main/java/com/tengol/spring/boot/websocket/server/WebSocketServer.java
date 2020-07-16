package com.tengol.spring.boot.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocketServer
 *
 * @author dongrui
 * @date 2020/7/16 14:40
 */
@Slf4j
@Component
@ServerEndpoint("/imServer/{userId}")
public class WebSocketServer {
    /**
     * 静态变量，用来记录当前在线连接数，应该把它设计成线程安全的。
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 用来存放每个客户端对应的  MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private String userId;

    /**
     * 连接建立成功时调用此方法
     *
     * @param session 客户端会话
     * @param userId  用户 ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        // 加入到服务器列表
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
        } else {
            webSocketMap.put(userId, this);
            // 在线数 + 1
            onlineCount.getAndIncrement();
        }
        log.info("用户 {} 连接成功，当前在线人数为：{}", userId, onlineCount);

        // 向客户端发送消息
        try {
            sendMessage("连接成功");
        } catch (IOException ie) {
            log.error("消息发送失败：{}", ie.getMessage(), ie);
        }
    }

    private void sendMessage(String message) throws IOException {
        // 服务器主动发送消息
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 关闭连接时调用此方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            onlineCount.getAndDecrement();
        }
        log.info("用户 {} 已退出，当前在线人数为：{}", userId, onlineCount);
    }

    /**
     * 收到消息时调用此方法
     *
     * @param message 收到的消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自用户 {} 的消息：{}", userId, message);
        if (Objects.nonNull(message) && StringUtils.hasText(message.trim())) {
            // 解析发送的报文
            JSONObject jsonObject = JSON.parseObject(message);
            // 追加发送人（防止篡改）
            jsonObject.put("fromUserId", this.userId);
            // 向指定人员转发
            String toUserId = jsonObject.getString("toUserId");
            if (StringUtils.hasText(toUserId) && webSocketMap.containsKey(toUserId)) {
                WebSocketServer ss = webSocketMap.get(toUserId);
                try {
                    ss.sendMessage(jsonObject.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                log.error("用户 {} 不在该服务器上！", toUserId);
            }
        }
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.hasText(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户 {} 不在线！", userId);
        }
    }

    public static int getOnlineCount(){
        return onlineCount.intValue();
    }

}
