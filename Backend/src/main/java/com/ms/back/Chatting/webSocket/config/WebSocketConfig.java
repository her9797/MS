package com.ms.back.Chatting.webSocket.config;

import com.ms.back.Chatting.webSocket.handler.SocketHandler;
import com.ms.back.Chatting.webSocket.interceptor.CustomHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final SocketHandler socketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 웹소켓 cors 정책으로 인해, 허용 도메인을 지정
        registry.addHandler(socketHandler, "/wss/chatting")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), new CustomHandshakeInterceptor())
                .setAllowedOrigins("*");
    }

}
