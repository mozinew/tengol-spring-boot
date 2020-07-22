package com.tengol.spring.boot.websocket.config;

import com.tengol.spring.boot.websocket.socket.PrincipalHandshakeHandler;
import com.tengol.spring.boot.websocket.socket.WebSocketDecoratorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * WebSocketConfigForToken
 *
 * @author dongrui
 * @date 2020/7/16 17:40
 */
@Slf4j
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfigForToken implements WebSocketMessageBrokerConfigurer {
    private WebSocketDecoratorFactory webSocketDecoratorFactory;
    private PrincipalHandshakeHandler principalHandshakeHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // myUrl表示 你前端到时要对应url映射
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .setHandshakeHandler(principalHandshakeHandler)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * queue 点对点
         * topic 广播
         * user 点对点前缀
         **/
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(webSocketDecoratorFactory);
    }
}
