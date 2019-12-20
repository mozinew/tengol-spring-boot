package com.tengol.spring.boot.websocket.websocket;

import com.tengol.spring.boot.websocket.util.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HelloWorldServer
 *
 * @author dongrui
 * @date 2019/12/20 9:40
 */
@Slf4j
@Component
@ServerEndpoint("/hello/socket/{sid}")
public class HelloWebSocketServer {
    //静态变量，记录当前在线连接数，需要设计成线程安全
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<HelloWebSocketServer> webSocketClientSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid = Strings.EMPTY;


    /**
     * 连接建立成功时调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        //加入到客户端Set集合
        webSocketClientSet.add(this);
        //在线数+1
        onlineCount.getAndIncrement();

        log.info("有新窗口开始监听：{}，当前在线人数为：{}", sid, onlineCount.get());

        this.sid = sid;

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从客户端Set集合清除
        webSocketClientSet.remove(this);
        //在线数-1
        onlineCount.getAndDecrement();

        log.info("窗口{}断开连接，当前连接数变为：{}", sid, onlineCount.get());
    }


    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口{}的消息：{}", sid, message);
        //群发消息
        for (HelloWebSocketServer item : webSocketClientSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                log.error("群发消息失败：向窗口{}发送消息失败，失败原因：{}", item.sid, e.getMessage());
            }
            sendMessage(session, "已将消息群发给其它客户端");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        sendMessage(session, "发生异常：" + error.getMessage());
        log.error("窗口{}发生异常：{}", sid, error.getMessage(), error);
    }

    /**
     * 实现服务器主动推送消息
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 实现服务器主动推送消息到特定窗口
     */
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("给窗口{}答复消息时发生异常：{}", sid, e.getMessage(), e);
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (HelloWebSocketServer item : webSocketClientSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
