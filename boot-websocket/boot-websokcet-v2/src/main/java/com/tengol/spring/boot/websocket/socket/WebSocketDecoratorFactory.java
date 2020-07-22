package com.tengol.spring.boot.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;
import java.util.Objects;

/**
 * 服务端和客户端在进行握手挥手时会被执行
 *
 * @author dongrui
 * @date 2020/7/16 17:22
 */
@Slf4j
public class WebSocketDecoratorFactory implements WebSocketHandlerDecoratorFactory {
    @Override
    public WebSocketHandler decorate(WebSocketHandler webSocketHandler) {
        return new WebSocketHandlerDecorator(webSocketHandler){
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                log.info("有新的连接产生：{}", session.getId());

                // 是否进行鉴权认证
                Principal principal = session.getPrincipal();
                if(Objects.nonNull(principal)){
                    log.info("该连接的认证信息：{}", principal.getName());
                    // 如果认证通过，则加入到 socket 管理器
                    if(Objects.nonNull(principal.getName()) && principal.getName().trim().equals("zhangsan")){
                        SocketManager.add(principal.getName(), session);
                    }
                }

                super.afterConnectionEstablished(session);
            }

            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                log.info("连接 {} 发送消息：{}", session.getId(), message);
                super.handleMessage(session, message);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                log.info("连接要退出了：{}", session.getId());

                Principal principal = session.getPrincipal();
                if(Objects.nonNull(principal) && Objects.nonNull(principal.getName()) && principal.getName().trim().equals("zhangsan")){
                    SocketManager.remove(principal.getName());
                }

                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
